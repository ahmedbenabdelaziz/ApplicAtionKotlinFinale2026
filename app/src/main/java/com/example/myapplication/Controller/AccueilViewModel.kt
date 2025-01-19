package com.example.porjetmobile2025.Controller

import android.content.Context
import android.content.Intent
import com.example.porjetmobile2025.View.SignUpActivity

import com.example.porjetmobile2025.Model.Article
import com.google.firebase.database.*

class AccueilViewModel {

    private val database: DatabaseReference = FirebaseDatabase.getInstance().reference.child("articles")

    fun loadArticles(onArticleLoaded: (Article) -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (articleSnapshot in snapshot.children) {
                    val article = articleSnapshot.getValue(Article::class.java)
                    if (article != null) {
                        onArticleLoaded(article)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}
