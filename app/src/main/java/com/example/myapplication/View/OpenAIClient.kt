package com.example.projetmobile2025

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

class OpenAIClient(private val apiKey: String) {

    private val client = OkHttpClient()

    fun getChatGPTResponse(userInput: String, callback: (String?) -> Unit) {
        val url = "https://api.openai.com/v1/chat/completions"
        val json = """
            {
                "model": "gpt-3.5-turbo",
                "messages": [{"role": "user", "content": "$userInput"}]
            }
        """

        // Utilisation de toMediaType() et toRequestBody()
        val body = json.toRequestBody("application/json".toMediaType())
        val request = Request.Builder()
            .url(url)
            .header("Authorization", "Bearer $apiKey")
            .post(body)
            .build()

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

                    // Utilisation de `response.body` au lieu de `response.body()`
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
