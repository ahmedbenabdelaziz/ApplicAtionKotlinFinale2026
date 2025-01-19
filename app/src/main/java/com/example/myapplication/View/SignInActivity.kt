package com.example.porjetmobile2025.View

<<<<<<< HEAD
=======
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import com.example.porjetmobile2025.ChatGPTActivity
import android.content.Intent
>>>>>>> origin/main
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.Controller.SignInController
import com.example.porjetmobile2025.R

class SignInActivity : AppCompatActivity() {

    private lateinit var signInController: SignInController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connexion)

        signInController = SignInController()

        val signInButton: Button = findViewById(R.id.signeInButton)

        signInButton.setOnClickListener {
<<<<<<< HEAD
            signInController.redirectToSignUp(this)
=======
            // Lancer la page d'inscription (SignUpActivity)
            val intent = Intent(this, ChatGPTActivity::class.java)
            startActivity(intent)
>>>>>>> origin/main
        }
    }
}
