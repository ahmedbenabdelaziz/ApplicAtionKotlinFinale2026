package com.example.porjetmobile2025.View

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.porjetmobile2025.Model.Article
import com.example.porjetmobile2025.R
import com.example.porjetmobile2025.ViewModel.AccueilViewModel

class AccueilActivity : AppCompatActivity() {

    private lateinit var viewModel: AccueilViewModel

    private lateinit var articleTextView: TextView
    private lateinit var articlePublishedTextView: TextView
    private lateinit var articleContentTextView: TextView
    private lateinit var articleImageView: ImageView
    private lateinit var articleImageViewMain: ImageView
    private lateinit var likesTextView: TextView
    private lateinit var commentsTextView: TextView
    private lateinit var btnAddArticle: Button
    private lateinit var calculatorImageView: ImageView
    private lateinit var xImageView: ImageView
    private lateinit var doctorLayout: LinearLayout
    private lateinit var medicamentLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pageacce)

        viewModel = AccueilViewModel()
        initializeViews()
        setupNavigation()
        loadArticleData()
    }

    private fun initializeViews() {
        articleTextView = findViewById(R.id.articleTextView)
        articlePublishedTextView = findViewById(R.id.articlePublishedTextView)
        articleContentTextView = findViewById(R.id.articleContentTextView)
        articleImageView = findViewById(R.id.articleImageView)
        articleImageViewMain = findViewById(R.id.articleImageViewMain)
        likesTextView = findViewById(R.id.likesTextView)
        commentsTextView = findViewById(R.id.commentsTextView)
        btnAddArticle = findViewById(R.id.btn_add_article)
        calculatorImageView = findViewById(R.id.calculatorImageView)
        xImageView = findViewById(R.id.xrrrrrrImageView)
        doctorLayout = findViewById(R.id.doctorLayout)
        medicamentLayout = findViewById(R.id.medicamentLayout)
    }

    private fun setupNavigation() {
        findViewById<ImageView>(R.id.ai).setOnClickListener {
            startActivity(Intent(this, ChatBotActivity::class.java))
        }

        btnAddArticle.setOnClickListener {
            startActivity(Intent(this, AddPostActivity::class.java))
        }

        findViewById<ImageView>(R.id.notificationImageView).setOnClickListener {
            startActivity(Intent(this, MyAppointmentsNotifActivity::class.java))
        }

        findViewById<ImageView>(R.id.comp).setOnClickListener {
            startActivity(Intent(this, EditProfileActivity::class.java))
        }

        xImageView.setOnClickListener {
            startActivity(Intent(this, CalculActivity::class.java))
        }

        calculatorImageView.setOnClickListener {
            startActivity(Intent(this, CalculatortwoActivity::class.java))
        }

        doctorLayout.setOnClickListener {
            startActivity(Intent(this, MyDoctorActivity::class.java))
        }

        medicamentLayout.setOnClickListener {
            startActivity(Intent(this, MedicamentActivity::class.java))
        }
    }

    private fun loadArticleData() {
        viewModel.loadArticles { article -> updateArticleView(article) }
    }

    private fun updateArticleView(article: Article) {
        articleTextView.text = article.title
        articlePublishedTextView.text = article.published
        articleContentTextView.text = article.comment

        Glide.with(this).load(article.imageUrl).into(articleImageView)
        Glide.with(this).load(article.mainImageUrl).into(articleImageViewMain)

        likesTextView.text = "1 Likes"
        commentsTextView.text = "1 Commentaires"
    }
}
