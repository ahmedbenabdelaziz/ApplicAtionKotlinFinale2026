package com.example.porjetmobile2025

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class ChatGPTActivity : AppCompatActivity() {

    // Remplacez par votre clé API OpenAI valide
    private val apiKey = "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"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activityy_main) // Assurez-vous que le fichier XML existe.

        val editTextInput = findViewById<EditText>(R.id.editTextInput)
        val buttonAsk = findViewById<Button>(R.id.buttonAsk)
        val textViewResponse = findViewById<TextView>(R.id.textViewResponse)

        buttonAsk.setOnClickListener {
            val userInput = editTextInput.text.toString().trim()
            if (userInput.isNotEmpty()) {
                getResponseFromChatGPT(userInput) { response ->
                    runOnUiThread {
                        if (response != null) {
                            textViewResponse.text = response
                        } else {
                            Toast.makeText(
                                this,
                                "Erreur de connexion ou réponse vide",
                                Toast.LENGTH_SHORT
                            ).show()
                            textViewResponse.text = "Aucune réponse reçue."
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Veuillez entrer une question.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getResponseFromChatGPT(userInput: String, callback: (String?) -> Unit) {
        val url = "https://api.openai.com/v1/chat/completions"
        val json = """
        {
            "model": "gpt-3.5-turbo",
            "messages": [{"role": "user", "content": "$userInput"}]
        }
    """
        val body = json.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url(url)
            .header("Authorization", "Bearer $apiKey") // Ajoutez la clé API ici
            .post(body)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) {
                        callback(null)
                        return
                    }
                    val responseBody = it.body
                    if (responseBody != null) {
                        val jsonResponse = JSONObject(responseBody.string())
                        val choices = jsonResponse.getJSONArray("choices")
                        val message = choices.getJSONObject(0)
                            .getJSONObject("message")
                            .getString("content")
                        callback(message)
                    } else {
                        callback(null)
                    }
                }
            }
        })
    }
}