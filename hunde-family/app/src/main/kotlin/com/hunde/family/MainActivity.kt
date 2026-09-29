package com.hunde.family

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        // 1. NAMA DURAAN LOGGED IN TA'E CHECK GOCHUU
        if (auth.currentUser != null) {
            // Yoo fayyadamaan duraan seeneera ta'e, fuula chat/home tti geessi
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish() // MainActivity cufii
            return
        }

        setContentView(R.layout.activity_main)

        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val btnCreateAccount = findViewById<Button>(R.id.btnCreateAccount)
        val btnGoogle = findViewById<Button>(R.id.btnGoogle)

        // Login tuqamnaan gara LoginActivity geessa
        btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // Create Account tuqamnaan gara RegisterActivity geessa
        btnCreateAccount.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        btnGoogle.setOnClickListener {
            Toast.makeText(this, "Google Sign-In yeroo dhiyootti...", Toast.LENGTH_SHORT).show()
        }
    }
}
