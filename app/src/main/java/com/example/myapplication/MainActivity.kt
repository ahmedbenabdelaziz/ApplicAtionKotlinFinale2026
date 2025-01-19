package com.example.porjetmobile2025

import com.example.projetmobile2025.OpenAIClient
import com.google.firebase.auth.FirebaseAuth
import android.content.Intent
import com.example.projetmobile2025.ChatGPTHandler
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.View.ForgotPasswordActivity
import com.example.porjetmobile2025.View.SignUpActivity
import com.example.porjetmobile2025.View.AccueilActivity

class MainActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var signInButton: Button
    private lateinit var signUpTextView: TextView
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion)

        firebaseAuth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        signInButton = findViewById(R.id.signeInButton)
        signUpTextView = findViewById(R.id.signUpTextView)
        val forgotPasswordText: TextView = findViewById(R.id.forgot_password_text)

        signInButton.setOnClickListener { validateInputs() }
        signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        forgotPasswordText.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        setupChatGPTHandler()
    }

    private fun validateInputs() {
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()

        if (email.isEmpty()) {
            Toast.makeText(this, "L'adresse e-mail est vide", Toast.LENGTH_SHORT).show()
            return
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Adresse e-mail incorrecte", Toast.LENGTH_SHORT).show()
            return
        }

        if (password.isEmpty()) {
            Toast.makeText(this, "Le mot de passe est vide", Toast.LENGTH_SHORT).show()
            return
        }

        signIn(email, password)
    }

    private fun signIn(email: String, password: String) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Connexion réussie", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, AccueilActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Échec de connexion: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun setupChatGPTHandler() {
        val editTextInput = findViewById<EditText>(R.id.editTextInput)
        val buttonAsk = findViewById<Button>(R.id.buttonAsk)
        val textViewResponse = findViewById<TextView>(R.id.textViewResponse)

        if (editTextInput == null || buttonAsk == null || textViewResponse == null) {
            Toast.makeText(this, "Erreur : Certaines vues n'ont pas été trouvées dans le layout.", Toast.LENGTH_SHORT).show()
            return
        }

        val openAIClient = OpenAIClient("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")
        val chatGPTHandler = ChatGPTHandler(
            context = this,
            openAIClient = openAIClient,
            editTextInput = editTextInput,
            buttonAsk = buttonAsk,
            textViewResponse = textViewResponse
        )
        chatGPTHandler.initialize()
    }
}
