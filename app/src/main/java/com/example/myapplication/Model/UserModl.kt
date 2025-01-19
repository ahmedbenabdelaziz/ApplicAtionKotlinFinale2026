package com.example.porjetmobile2025.Model

import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import android.content.Context

class UserModel(private val firebaseAuth: FirebaseAuth) {

    fun registerUser(email: String, password: String, context: Context, onComplete: (Boolean, String) -> Unit) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onComplete(true, "Inscription réussie !")
                } else {
                    onComplete(false, "Erreur : ${task.exception?.message}")
                }
            }
    }

    fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }
}
