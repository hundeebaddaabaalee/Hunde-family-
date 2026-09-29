package com.hunde.family

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {

    private lateinit var dbRef: DatabaseReference
    private lateinit var currentUserId: String
    private var receiverUserId: String = "" // Profile/Chat irraa kan dhihaatu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        currentUserId = FirebaseAuth.getInstance().currentUser?.uid ?: ""
        receiverUserId = intent.getStringExtra("receiverId") ?: "default_receiver"

        // Firebase path chats
        val chatRoomId = if (currentUserId < receiverUserId) {
            "${currentUserId}_${receiverUserId}"
        } else {
            "${receiverUserId}_${currentUserId}"
        }

        dbRef = FirebaseDatabase.getInstance().getReference("chats").child(chatRoomId)

        val btnBackChat = findViewById<Button>(R.id.btnBackChat)
        val btnVoiceCall = findViewById<Button>(R.id.btnVoiceCall)
        val btnVideoCall = findViewById<Button>(R.id.btnVideoCall)
        val btnAttach = findViewById<Button>(R.id.btnAttach)
        val btnCamera = findViewById<Button>(R.id.btnCamera)
        val btnVoiceRecord = findViewById<Button>(R.id.btnVoiceRecord)
        val btnSend = findViewById<Button>(R.id.btnSend)
        val etMessage = findViewById<EditText>(R.id.etMessage)
        val containerMessages = findViewById<LinearLayout>(R.id.containerMessages)

        btnBackChat.setOnClickListener { finish() }

        btnVoiceCall.setOnClickListener {
            Toast.makeText(this, "Bilbila Sagalee (Voice Call)...", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:0912345678")
            startActivity(intent)
        }

        btnVideoCall.setOnClickListener {
            Toast.makeText(this, "Bilbila Suur-sagalee (Video Call)...", Toast.LENGTH_SHORT).show()
        }

        btnAttach.setOnClickListener {
            Toast.makeText(this, "Faayila/Doc/Audio filadhu...", Toast.LENGTH_SHORT).show()
        }

        btnCamera.setOnClickListener {
            Toast.makeText(this, "Suuraa Kaasi/Galaariidhaa filadhu...", Toast.LENGTH_SHORT).show()
        }

        btnVoiceRecord.setOnClickListener {
            Toast.makeText(this, "Sagalee waraabaa jira (Voice Record)...", Toast.LENGTH_SHORT).show()
        }

        // Send message to Firebase
        btnSend.setOnClickListener {
            val text = etMessage.text.toString().trim()
            if (text.isNotEmpty()) {
                val msgMap = hashMapOf(
                    "senderId" to currentUserId,
                    "message" to text,
                    "timestamp" to System.currentTimeMillis()
                )

                dbRef.push().setValue(msgMap).addOnSuccessListener {
                    etMessage.setText("")
                }.addOnFailureListener {
                    Toast.makeText(this, "Ergaan hin ergamne!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Listen for new messages from Firebase real-time
        listenForMessages(containerMessages)
    }

    private fun listenForMessages(containerMessages: LinearLayout) {
        dbRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    containerMessages.removeAllViews()
                    for (postSnapshot in snapshot.children) {
                        val message = postSnapshot.child("message").value.toString()
                        val senderId = postSnapshot.child("senderId").value.toString()

                        val textView = TextView(this@ChatActivity).apply {
                            this.text = message
                            this.setPadding(24, 16, 24, 16)
                            this.textSize = 15f

                            val params = LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT
                            ).apply {
                                bottomMargin = 16
                            }

                            if (senderId == currentUserId) {
                                // Ergaa ati ergite (Mirga)
                                this.setTextColor(0xFF111111.toInt())
                                this.setBackgroundColor(0xFFDCF8C6.toInt())
                                params.gravity = Gravity.END
                            } else {
                                // Ergaa gama biraatii dhufe (Bitaa)
                                this.setTextColor(0xFF000000.toInt())
                                this.setBackgroundColor(0xFFFFFFFF.toInt())
                                params.gravity = Gravity.START
                            }
                            this.layoutParams = params
                        }
                        containerMessages.addView(textView)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@ChatActivity, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
                }
        })
    }
}
