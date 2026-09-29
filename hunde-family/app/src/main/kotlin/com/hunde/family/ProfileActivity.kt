package com.hunde.family

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class ProfileActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // View-idoota XML keessaa waamuu
        val ivProfilePicture = findViewById<ImageView>(R.id.ivProfilePicture)
        val etFullName = findViewById<EditText>(R.id.etFullName)
        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)
        val etOtpCode = findViewById<EditText>(R.id.etOtpCode)
        val etOldPassword = findViewById<EditText>(R.id.etOldPassword)
        val etNewPassword = findViewById<EditText>(R.id.etNewPassword)
        val btnSendOtp = findViewById<Button>(R.id.btnSendOtp)
        val btnSaveProfile = findViewById<Button>(R.id.btnSaveProfile)
        val btnBackToHome = findViewById<Button>(R.id.btnBackToHome)

        // 1. Suuraa Profile Jijjiirraaf
        ivProfilePicture.setOnClickListener {
            Toast.makeText(this, "Suuraa galeree keessaa filachuu...", Toast.LENGTH_SHORT).show()
        }

        // 2. Koodii OTP Erguuf
        btnSendOtp.setOnClickListener {
            val phone = etPhoneNumber.text.toString().trim()
            if (phone.isEmpty()) {
                etPhoneNumber.error = "Dura lakkoofsa bilbilaa galchaa!"
            } else {
                etOtpCode.visibility = View.VISIBLE
                Toast.makeText(this, "Koodiin mirkaneessaa $phone 'tti ergameera", Toast.LENGTH_LONG).show()
            }
        }

        // 3. Profile Save Gochuuf
        btnSaveProfile.setOnClickListener {
            val fullName = etFullName.text.toString().trim()
            val username = etUsername.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val oldPassword = etOldPassword.text.toString().trim()
            val newPassword = etNewPassword.text.toString().trim()

            // Verification (Mirkaneessa)
            if (fullName.isEmpty()) {
                etFullName.error = "Maqaa guutuu galchaa"
                return@setOnClickListener
            }

            if (username.isEmpty() || username.contains(" ") || username.length < 4) {
                etUsername.error = "Username sirrii galchaa (space malee, xiqqaate 4)"
                return@setOnClickListener
            }

            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Teessoo Email sirrii galchaa"
                return@setOnClickListener
            }

            if (oldPassword.isNotEmpty() && newPassword.isEmpty()) {
                etNewPassword.error = "Password haaraa galchaa"
                return@setOnClickListener
            }

            Toast.makeText(this, "Profile'n keessan milkaa'inaan ol-kaa'ameera!", Toast.LENGTH_SHORT).show()
        }

        // 4. Gara Home'tti Deebi'uuf
        btnBackToHome.setOnClickListener {
            finish()
        }
    }
}