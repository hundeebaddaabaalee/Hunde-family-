package com.hunde.family

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast

class StorageDataActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage_data)

        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack?.setOnClickListener { finish() }

        Toast.makeText(this, "Storage & Data screen", Toast.LENGTH_SHORT).show()
    }
}
