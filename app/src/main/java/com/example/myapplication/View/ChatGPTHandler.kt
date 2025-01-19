package com.example.projetmobile2025

import android.content.Context
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.porjetmobile2025.MainActivity

class ChatGPTHandler(
    private val context: Context,
    private val openAIClient: OpenAIClient,
    private val editTextInput: EditText,
    private val buttonAsk: Button,
    private val textViewResponse: TextView
) {
    fun initialize() {
        buttonAsk.setOnClickListener {
            val userInput = editTextInput.text.toString()
            if (userInput.isNotEmpty()) {
                getResponseFromChatGPT(userInput)
            } else {
                Toast.makeText(context, "Veuillez entrer une question.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getResponseFromChatGPT(userInput: String) {
        openAIClient.getChatGPTResponse(userInput) { response ->
            (context as? MainActivity)?.runOnUiThread {
                if (response != null) {
                    textViewResponse.text = response
                } else {
                    Toast.makeText(context, "Erreur de connexion ou réponse vide", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
