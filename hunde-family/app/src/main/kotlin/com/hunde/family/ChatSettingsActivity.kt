package com.hunde.family

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast

class ChatSettingsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_settings)

        // Generic type keessaa mallattoo '?' sana kaafnee sirreessineera
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        val itemWallpaper = findViewById<LinearLayout>(R.id.itemWallpaper)
        val itemAutoDownload = findViewById<LinearLayout>(R.id.itemAutoDownload)
        val itemFontSize = findViewById<LinearLayout>(R.id.itemFontSize)

        // Button gara duubatti deebi'u
        btnBack?.setOnClickListener {
            finish()
        }

        // Qindaa'ina Wallpaper
        itemWallpaper?.setOnClickListener {
            Toast.makeText(this, "Wallpaper settings...", Toast.LENGTH_SHORT).show()
        }

        // Qindaa'ina Auto-download
        itemAutoDownload?.setOnClickListener {
            Toast.makeText(this, "Auto-download settings...", Toast.LENGTH_SHORT).show()
        }

        // Qindaa'ina Font size
        itemFontSize?.setOnClickListener {
            Toast.makeText(this, "Font size settings...", Toast.LENGTH_SHORT).show()
        }
    }
}
