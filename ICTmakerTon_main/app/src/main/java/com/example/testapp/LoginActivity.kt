package com.example.testapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val intent = Intent(this, MainActivity::class.java)

        val button: Button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener {
            startActivity(intent)
        }

    }
}