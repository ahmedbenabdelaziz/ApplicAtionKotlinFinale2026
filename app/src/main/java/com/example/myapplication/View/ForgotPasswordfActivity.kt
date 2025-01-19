package com.example.porjetmobile2025.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.MainActivity
import com.example.porjetmobile2025.R

class ForgotPasswordfActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_code)

        val sendCodeButton: Button = findViewById(R.id.btn_reset_password)

        sendCodeButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
