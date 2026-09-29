package com.hunde.family

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceView
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.RtcEngineConfig
import io.agora.rtc.video.VideoCanvas

class CallActivity : AppCompatActivity() {

    // Agora App ID kee asitti galchita
    private val appId = "YOUR_AGORA_APP_ID_HERE" 
    private val channelName = "hunde_family_room"
    private val token: String? = null 

    private var mRtcEngine: RtcEngine? = null
    private var isMuted = false

    private lateinit var localContainer: FrameLayout
    private lateinit var remoteContainer: FrameLayout
    private lateinit var btnMute: ImageButton
    private lateinit var btnEndCall: ImageButton
    private lateinit var btnSwitchCamera: ImageButton
    private lateinit var tvCallStatus: TextView

    private val PERMISSION_REQ_ID = 22
    private val REQUESTED_PERMISSIONS = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.CAMERA
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call)

        localContainer = findViewById(R.id.local_video_view_container)
        remoteContainer = findViewById(R.id.remote_video_view_container)
        btnMute = findViewById(R.id.btnMute)
        btnEndCall = findViewById(R.id.btnEndCall)
        btnSwitchCamera = findViewById(R.id.btnSwitchCamera)
        tvCallStatus = findViewById(R.id.tvCallStatus)

        if (checkSelfPermission()) {
            initAgoraEngineAndJoinChannel()
        } else {
            ActivityCompat.requestPermissions(this, REQUESTED_PERMISSIONS, PERMISSION_REQ_ID)
        }

        btnEndCall.setOnClickListener {
            leaveChannel()
            finish()
        }

        btnMute.setOnClickListener {
            isMuted = !isMuted
            mRtcEngine?.muteLocalAudioStream(isMuted)
            Toast.makeText(this, if (isMuted) "Mic Muted" else "Mic Unmuted", Toast.LENGTH_SHORT).show()
        }

        btnSwitchCamera.setOnClickListener {
            mRtcEngine?.switchCamera()
        }
    }

    private fun checkSelfPermission(): Boolean {
        return ContextCompat.checkSelfPermission(this, REQUESTED_PERMISSIONS[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, REQUESTED_PERMISSIONS[1]) == PackageManager.PERMISSION_GRANTED
    }

    private fun initAgoraEngineAndJoinChannel() {
        try {
            val config = RtcEngineConfig()
            config.mContext = baseContext
            config.mAppId = appId
            config.mEventHandler = mRtcEventHandler
            mRtcEngine = RtcEngine.create(config)

            mRtcEngine?.enableVideo()
            
            // Setup Local Video
            val surfaceView = RtcEngine.CreateRendererView(baseContext)
            localContainer.addView(surfaceView)
            mRtcEngine?.setupLocalVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, 0))

            // Join Channel
            mRtcEngine?.joinChannel(token, channelName, "", 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private val mRtcEventHandler = object : IRtcEngineEventHandler() {
        override fun onUserJoined(uid: Int, elapsed: Int) {
            runOnUiThread {
                tvCallStatus.text = "Connected"
                setupRemoteVideo(uid)
            }
        }

        override fun onUserOffline(uid: Int, reason: Int) {
            runOnUiThread {
                tvCallStatus.text = "User disconnected"
                remoteContainer.removeAllViews()
            }
        }
    }

    private fun setupRemoteVideo(uid: Int) {
        val surfaceView = RtcEngine.CreateRendererView(baseContext)
        remoteContainer.addView(surfaceView)
        mRtcEngine?.setupRemoteVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, uid))
    }

    private fun leaveChannel() {
        mRtcEngine?.leaveChannel()
    }

    override fun onDestroy() {
        super.onDestroy()
        leaveChannel()
        RtcEngine.destroy()
    }
}
