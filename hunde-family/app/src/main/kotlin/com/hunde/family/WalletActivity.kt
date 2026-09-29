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

class WalletActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        val btnAddMoney = findViewById<Button>(R.id.btnAddMoney)

        btnAddMoney?.setOnClickListener {
            Toast.makeText(this, "Kaffaltiin dhiyootti hojii irra oola!", Toast.LENGTH_SHORT).show()
        }
    }
}
