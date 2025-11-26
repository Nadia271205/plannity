package com.proyek.planity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_regis)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val username = findViewById<EditText>(R.id.etUsername)
        val email = findViewById<EditText>(R.id.etRegisEmailAddress)
        val password = findViewById<EditText>(R.id.etRegisPassword)
        val btnregis = findViewById<Button>(R.id.btnRegis)

        btnregis.setOnClickListener {
            val email : String = email.text.toString()
            val password : String = password.text.toString()
            val intent = Intent(this, LoginActivity::class.java)
            intent.putExtra("emailregis", email)
            intent.putExtra("passwordregis", password)
            startActivity(intent)
        }
    }
}