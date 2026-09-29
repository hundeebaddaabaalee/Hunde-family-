package com.hunde.family

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class NotificationsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifications)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack?.setOnClickListener { finish() }

        Toast.makeText(this, "Notifications screen", Toast.LENGTH_SHORT).show()
    }
}
