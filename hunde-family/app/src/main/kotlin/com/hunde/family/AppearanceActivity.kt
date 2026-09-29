package com.hunde.family

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

class AppearanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appearance)

        val rgThemeMode = findViewById<RadioGroup>(R.id.rgThemeMode)
        val rbSystemDefault = findViewById<RadioButton>(R.id.rbSystemDefault)
        val rbLightMode = findViewById<RadioButton>(R.id.rbLightMode)
        val rbDarkMode = findViewById<RadioButton>(R.id.rbDarkMode)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Preferences irraa mode duran filatame dubbisuu
        val sharedPref = getSharedPreferences("ThemePrefs", Context.MODE_PRIVATE)
        val currentMode = sharedPref.getInt("MODE", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        when (currentMode) {
            AppCompatDelegate.MODE_NIGHT_NO -> rbLightMode.isChecked = true
            AppCompatDelegate.MODE_NIGHT_YES -> rbDarkMode.isChecked = true
            else -> rbSystemDefault.isChecked = true
        }

        // Mode jijjiirame ol-kaayuu fi hojiirra oolchuu
        rgThemeMode.setOnCheckedChangeListener { _, checkedId ->
            val mode = when (checkedId) {
                R.id.rbLightMode -> AppCompatDelegate.MODE_NIGHT_NO
                R.id.rbDarkMode -> AppCompatDelegate.MODE_NIGHT_YES
                else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            }

            // Mode olkaayuu
            sharedPref.edit().putInt("MODE", mode).apply()
            
            // Appii irratti battalatti jijjiirraa apply gochuu
            AppCompatDelegate.setDefaultNightMode(mode)
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}
