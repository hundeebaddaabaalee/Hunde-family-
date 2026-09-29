package com.hunde.family

import com.hunde.family.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast

class SettingsActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // --- 1. Sadan Gubbaa (Data Gift, Wallet) ---
        val itemDataGift = findViewById<LinearLayout>(R.id.itemDataGift)
        val itemWallet = findViewById<LinearLayout>(R.id.itemWallet)

        // Data Gift tuqamnaan gara DataGiftActivity geessa
        itemDataGift?.setOnClickListener {
            val intent = Intent(this, DataGiftActivity::class.java)
            startActivity(intent)
        }

        // Wallet tuqamnaan gara WalletActivity geessa
        itemWallet?.setOnClickListener {
            val intent = Intent(this, WalletActivity::class.java)
            startActivity(intent)
        }

        // 1. View id waamuu
        val itemAccount = findViewById<LinearLayout>(R.id.itemAccount)

        // 2. Click listener itti dabalanii fuula ProfileActivity banuu
        itemAccount?.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        //--- fuula ChatSettingsActivity()

        itemAccount?.setOnClickListener {
            val intent = Intent(this, ProfileActivity()::class.java)
            startActivity(intent)
        }

        // --- 2. setting and privacy---

        val itemNotifications = findViewById<LinearLayout>(R.id.itemNotifications)
        val itemPrivacy = findViewById<LinearLayout>(R.id.itemPrivacy)
        val itemAppearance = findViewById<LinearLayout>(R.id.itemAppearance)
        val itemLanguage = findViewById<LinearLayout>(R.id.itemLanguage)
        val itemChatSettings = findViewById<LinearLayout>(R.id.itemChatSettings)
        val itemStorage = findViewById<LinearLayout>(R.id.itemStorage)
        val itemHelp = findViewById<LinearLayout>(R.id.itemHelp)

        itemNotifications?.setOnClickListener {
            val intent = Intent(this, NotificationsActivity()
            ::class.java)
            startActivity(intent)
        }

        itemChatSettings?.setOnClickListener {
            val intent = Intent(this, ChatSettingsActivity()::class.java)
            startActivity(intent)
        }

        // --- 2. setting and privacy---
        itemPrivacy?.setOnClickListener {
            val intent = Intent(this, PrivacySettingsActivity()::class.java)
            startActivity(intent)
        }

        itemAppearance?.setOnClickListener {
            Toast.makeText(this, "Appearance Settings...", Toast.LENGTH_SHORT).show()
        }

        itemLanguage?.setOnClickListener {
            Toast.makeText(this, "Language Settings...", Toast.LENGTH_SHORT).show()
        }

        itemStorage?.setOnClickListener {
            val intent = Intent(this, StorageDataActivity()::class.java)
            startActivity(intent)
        }

        itemHelp?.setOnClickListener {
            Toast.makeText(this, "Help & Support...", Toast.LENGTH_SHORT).show()
        }
    }
}
