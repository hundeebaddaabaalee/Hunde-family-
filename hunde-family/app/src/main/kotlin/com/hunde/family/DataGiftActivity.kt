package com.hunde.family

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DataGiftActivity : AppCompatActivity() {

    private lateinit var etPhoneNumber: EditText
    private lateinit var spinnerPackages: Spinner
    private lateinit var tvTotalPrice: TextView
    private lateinit var btnSendGift: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_gift)

        // Type casting dabalamee sirreeffameera (Type mismatch dhabamsiisa)
        etPhoneNumber = findViewById<EditText>(R.id.etPhoneNumber)
        spinnerPackages = findViewById<Spinner>(R.id.spinnerPackages)
        tvTotalPrice = findViewById<TextView>(R.id.tvTotalPrice)
        btnSendGift = findViewById<Button>(R.id.btnSendGift)

        val btnFamily1 = findViewById<LinearLayout>(R.id.btnFamily1)
        val btnFamily2 = findViewById<LinearLayout>(R.id.btnFamily2)
        val btnFamily3 = findViewById<LinearLayout>(R.id.btnFamily3)

        // Dropdown Paakeejii Dataa
        val packages = arrayOf("100 MB - 10 ETB", "500 MB - 35 ETB", "1 GB - 50 ETB", "2 GB - 90 ETB")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, packages)
        spinnerPackages.adapter = adapter

        // Quick Select Maatii
        btnFamily1.setOnClickListener {
            etPhoneNumber.setText("0912345678")
            Toast.makeText(this, "Obbo Caalaa filatameera", Toast.LENGTH_SHORT).show()
        }

        btnFamily2.setOnClickListener {
            etPhoneNumber.setText("0987654321")
            Toast.makeText(this, "Aaddee filatamtaniirtu", Toast.LENGTH_SHORT).show()
        }

        btnFamily3.setOnClickListener {
            etPhoneNumber.setText("0911223344")
            Toast.makeText(this, "Obboleessa filatameera", Toast.LENGTH_SHORT).show()
        }

        // Data Erguu Button
        btnSendGift.setOnClickListener {
            val phone = etPhoneNumber.text.toString().trim()
            val selectedPackage = spinnerPackages.selectedItem.toString()

            if (phone.isEmpty()) {
                Toast.makeText(this, "Maaloo lakkoofsa bilbilaa galchaa!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Paakeejiin ($selectedPackage) $phone -f ergamaa jira...", Toast.LENGTH_LONG).show()
            }
        }
    }
}
