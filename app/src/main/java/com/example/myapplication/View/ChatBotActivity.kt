package com.example.myapplication.View

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import java.io.IOException
import com.example.porjetmobile2025.R

class ChatBotActivity : AppCompatActivity() {

    private val url = "https://api.openai.com/v1/completions"

    // Clé API lue depuis les ressources
    private val apiKey: String by lazy {
        // Utilisez une méthode sécurisée pour obtenir la clé API (ex. à partir de variables d'environnement ou d'un fichier sécurisé)
        "your-api-key-here"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity)

        val inputMessage = findViewById<EditText>(R.id.editTextMessage)
        val sendButton = findViewById<Button>(R.id.buttonSend)
        val responseView = findViewById<TextView>(R.id.textViewResponse)

        sendButton.setOnClickListener {
            val userMessage = inputMessage.text.toString()
            if (userMessage.isNotBlank()) {
                sendMessageToChatbot(userMessage, responseView)
                inputMessage.text.clear()
            } else {
                Toast.makeText(this, "Veuillez entrer un message", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun sendMessageToChatbot(message: String, responseView: TextView) {
        val client = OkHttpClient()

        val jsonBody = JSONObject().apply {
            put("model", "text-davinci-003")
            put("prompt", message)
            put("max_tokens", 100)
        }

        val requestBody = RequestBody.create(
            "application/json".toMediaTypeOrNull(),
            jsonBody.toString()
        )

        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", "Bearer $apiKey")  // Utilisation de la clé API depuis la variable
            .post(requestBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(
                        this@ChatBotActivity,
                        "Erreur de connexion au serveur",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val botResponse = JSONObject(responseBody ?: "")
                        .getJSONArray("choices")
                        .getJSONObject(0)
                        .getString("text")
                    runOnUiThread {
                        responseView.text = botResponse.trim()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            this@ChatBotActivity,
                            "Erreur : ${response.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }
}
