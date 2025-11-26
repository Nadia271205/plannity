package com.proyek.planity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: AppCompatButton
    private lateinit var tvRegisterLink: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        etEmail = findViewById<EditText>(R.id.etRegisEmailAddress)
        etPassword = findViewById<EditText>(R.id.etRegisPassword)
        btnLogin = findViewById<AppCompatButton>(R.id.btnRegis)
        tvRegisterLink = findViewById<TextView>(R.id.tvLoginLink)
        val akunEmail: String = intent.getStringExtra("admin@gmail.com").toString()
        val passwordEmail: String = intent.getStringExtra("admin123").toString()

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if(email == akunEmail && password == passwordEmail){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Login Successful! Email : $email", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Email atau Password salah, Silahkan coba lagi", Toast.LENGTH_SHORT).show()
            }
        }
    tvRegisterLink.setOnClickListener {
            val intent = Intent(this, RegisActivity::class.java)
            startActivity(intent)
        }
    }
}