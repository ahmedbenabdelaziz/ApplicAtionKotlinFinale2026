package com.example.porjetmobile2025.View

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.MainActivity
import com.example.porjetmobile2025.Model.UserModel
import com.example.porjetmobile2025.Controller.SignUpController
import com.example.porjetmobile2025.R
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var userModel: UserModel
    private lateinit var signUpController: SignUpController

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var signUpButton: Button
    private lateinit var signInRedirectTextView: TextView
    private lateinit var patientRadioButton: RadioButton
    private lateinit var medecinRadioButton: RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inscription)

        firebaseAuth = FirebaseAuth.getInstance()
        userModel = UserModel(firebaseAuth)
        signUpController = SignUpController(userModel, this)

        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        phoneEditText = findViewById(R.id.phoneEditText)
        signUpButton = findViewById(R.id.signUpButton)
        signInRedirectTextView = findViewById(R.id.signInRedirectTextView)
        patientRadioButton = findViewById(R.id.radio_patient)
        medecinRadioButton = findViewById(R.id.radio_medecin)

        signInRedirectTextView.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val name = nameEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val password = passwordEditText.text.toString().trim()
        val phone = phoneEditText.text.toString().trim()
        val role = if (patientRadioButton.isChecked) "Patient" else "Médecin"

        signUpController.registerUser(name, email, password, phone, role) { success, message ->
            if (success) {
                showToast(message)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                showToast(message)
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
