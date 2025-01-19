package com.example.firebaseauth.controller

class AuthController {
    fun validateInput(email: String, password: String): String? {
        return when {
            email.isEmpty() -> "Le champ email est vide."
            password.isEmpty() -> "Le champ mot de passe est vide."
            !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Email invalide"
            password.length < 6 -> "Le mot de passe doit contenir au moins 6 caractères."
            else -> null
        }
    }
}
