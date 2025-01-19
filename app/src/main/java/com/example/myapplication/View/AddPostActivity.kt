package com.example.porjetmobile2025.View

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.porjetmobile2025.Model.Article
import com.example.porjetmobile2025.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*

class AddPostActivity : AppCompatActivity() {

    private lateinit var commentEditText: EditText
    private lateinit var addPostButton: Button
    private lateinit var selectImageButton: Button
    private lateinit var backButtonImageView: ImageView

    private var imageUri: Uri? = null

    private lateinit var database: DatabaseReference
    private lateinit var storageReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_article)

        commentEditText = findViewById(R.id.commentEditText)
        addPostButton = findViewById(R.id.addPostButton)
        selectImageButton = findViewById(R.id.selectImageButton)
        backButtonImageView = findViewById(R.id.backButtonImageView)
        database = FirebaseDatabase.getInstance().reference.child("articles")
        storageReference = FirebaseStorage.getInstance().reference

        val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            imageUri = uri
            Toast.makeText(this, "Image sélectionnée", Toast.LENGTH_SHORT).show()
        }

        selectImageButton.setOnClickListener {
            getContent.launch("image/*")
        }

        backButtonImageView.setOnClickListener { finish() }

        addPostButton.setOnClickListener { uploadPost() }
    }

    private fun uploadPost() {
        val comment = commentEditText.text.toString().trim()
        if (comment.isEmpty() || imageUri == null) {
            Toast.makeText(this, "Ajoutez un commentaire et une image", Toast.LENGTH_SHORT).show()
            return
        }

        val uniqueId = UUID.randomUUID().toString()
        val imageRef = storageReference.child("articles/$uniqueId.jpg")

        imageRef.putFile(imageUri!!).addOnSuccessListener {
            imageRef.downloadUrl.addOnSuccessListener { imageUrl ->
                savePostToDatabase(comment, imageUrl.toString())
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Erreur d'upload de l'image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun savePostToDatabase(comment: String, imageUrl: String) {
        val article = Article(
            title = comment,
            comment = comment,
            imageUrl = imageUrl,
            mainImageUrl = imageUrl,
            published = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date()),
        )

        database.push().setValue(article).addOnSuccessListener {
            Toast.makeText(this, "Article ajouté avec succès", Toast.LENGTH_SHORT).show()
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, "Erreur lors de l'ajout de l'article", Toast.LENGTH_SHORT).show()
        }
    }
}
