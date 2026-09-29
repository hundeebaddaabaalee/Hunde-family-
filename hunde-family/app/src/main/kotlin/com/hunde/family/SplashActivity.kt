package com.hunde.family

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Firebase Auth initialize gochuu
        auth = FirebaseAuth.getInstance()

        // Sekondii 2.5 (2500 ms) booda ceesisa
        Handler(Looper.getMainLooper()).postDelayed({
            checkUserSession()
        }, 2500)
    }

    private fun checkUserSession() {
        val currentUser = auth.currentUser

        if (currentUser != null) {
            // Yoo user-n login ta'ee jiru ta'e
            // Hubachiisa: Email verify gochuu isaa dhiisus check gochuu yoo barbaadde:
            /*
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            */

            // Akka koodii kee kan duraatti: Kallattiidhaan gara HomeActivity'tti geessa
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        } else {
            // Yoo user-n login hin ta'in ta'e gara MainActivity (Welcome Screen) geessa
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        // Animashinii cufiinsaa fi baninsaa miidhagaa ta'e agarsiisuuf (Optional)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)

        // SplashActivity cufuu akka namni duubatti deebi'uun Splash hin agarsiisneef
        finish()
    }
}
