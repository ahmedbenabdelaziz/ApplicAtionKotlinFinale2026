package com.example.porjetmobile2025.Controller

import android.content.Context
import android.widget.Toast
import com.example.porjetmobile2025.Model.UserModel

class SignUpController(private val userModel: UserModel, private val context: Context) {

    fun registerUser(name: String, email: String, password: String, phone: String, role: String, onComplete: (Boolean, String) -> Unit) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty()) {
            onComplete(false, "Veuillez remplir tous les champs")
            return
        }

        if (!userModel.validateEmail(email)) {
            onComplete(false, "Adresse email invalide")
            return
        }

        if (!userModel.validatePassword(password)) {
            onComplete(false, "Le mot de passe doit avoir au moins 6 caractères")
            return
        }
        userModel.registerUser(email, password, context) { success, message ->
            onComplete(success, message)
        }
    }
}
