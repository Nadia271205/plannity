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
        etEmail = findViewById<EditText>(R.id.etTextEmailAddress)
        etPassword = findViewById<EditText>(R.id.etTextPassword)
        btnLogin = findViewById<AppCompatButton>(R.id.btnLogin)
        tvRegisterLink = findViewById<TextView>(R.id.tvRegisterLink)
        val akunEmail: String = intent.getStringExtra("emailregis").toString()
        val passwordEmail: String = intent.getStringExtra("passwordregis").toString()

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