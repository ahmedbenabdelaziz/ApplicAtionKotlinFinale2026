package com.example.porjetmobile2025.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.R

class ForgotPasswordTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgertpass3)

        val sendCodeButton: Button = findViewById(R.id.btn_verify_code)

        sendCodeButton.setOnClickListener {
            val intent = Intent(this, ForgotPasswordfActivity::class.java)
            startActivity(intent)
        }
    }
}
