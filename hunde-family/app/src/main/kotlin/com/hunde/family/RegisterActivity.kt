package com.hunde.family

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Firebase Instances Initialize gochuu
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()

        // XML Views ID isaaniitiin wal-qabsiisuu
        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etRegisterEmail)
        val etPassword = findViewById<EditText>(R.id.etRegisterPassword)
        val etConfirmPassword = findViewById<EditText>(R.id.etConfirmPassword)
        val btnRegisterSubmit = findViewById<Button>(R.id.btnRegisterSubmit)

        btnRegisterSubmit.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val confirmPassword = etConfirmPassword.text.toString().trim()

            // Field-oonni duudaa akka hin taane mirkaneessuu
            if (fullName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Sami! Bakka duudaa jiru guutii!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Password wal-fakkaachuu mirkaneessuu
            if (password != confirmPassword) {
                Toast.makeText(this, "Password'n wal hin fakkaatu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Firebase Auth irratti fayyadamaa haaraa uumuu
            auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = auth.currentUser?.uid ?: ""

                    if (uid.isNotEmpty()) {
                        // Oddeeffannoo fayyadamaa Realtime Database irratti olka'uu
                        val userMap = mapOf(
                            "uid" to uid,
                            "fullName" to fullName,
                            "username" to username,
                            "email" to email
                        )

                        database.reference.child("users").child(uid).setValue(userMap)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Galmeen milkaa'inaa xumurameera!", Toast.LENGTH_SHORT).show()
                            // Kallattiidhaan gara HomeActivity geessuu
                            startActivity(Intent(this, HomeActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Database Error: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
