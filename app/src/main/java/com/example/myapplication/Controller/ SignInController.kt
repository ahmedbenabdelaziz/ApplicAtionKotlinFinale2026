package com.example.porjetmobile2025.Controller

import android.content.Context
import android.content.Intent
import com.example.porjetmobile2025.View.SignUpActivity
import com.example.porjetmobile2025.View.SignInActivity

class SignInController {

    fun redirectToSignUp(context: Context) {
        val intent = Intent(context, SignUpActivity::class.java)
        context.startActivity(intent)
    }
}
