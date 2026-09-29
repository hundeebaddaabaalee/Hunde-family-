package com.hunde.family

import android.app.Activity // <-- 1. AppCompatActivity bakka bu'e
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

class PrivacySettingsActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_settings)

        val btnBackPrivacy = findViewById<Button>(R.id.btnBackPrivacy)
        val btnLastSeen = findViewById<LinearLayout>(R.id.btnLastSeen)
        val btnBlockedUsers = findViewById<LinearLayout>(R.id.btnBlockedUsers)
        val btnChangePassword = findViewById<LinearLayout>(R.id.btnChangePassword)

        btnBackPrivacy?.setOnClickListener { finish() }

        btnLastSeen?.setOnClickListener {
            Toast.makeText(this, "Filannoo Last Seen jijjiiri...", Toast.LENGTH_SHORT).show()
        }

        btnBlockedUsers?.setOnClickListener {
            Toast.makeText(this, "Tarree Blocked Users...", Toast.LENGTH_SHORT).show()
        }

        btnChangePassword?.setOnClickListener {
            Toast.makeText(this, "Password jijjiiruuf...", Toast.LENGTH_SHORT).show()
        }
    }
}
