package com.example.porjetmobile2025.View

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.porjetmobile2025.Controller.MedicamentController
import com.example.porjetmobile2025.R

class MedicamentActivity : AppCompatActivity() {

    private lateinit var medicamentController: MedicamentController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.medicamenet)

        medicamentController = MedicamentController()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })

        val medicaments = medicamentController.getMedicaments()
        medicaments.forEach {
        }
    }
}
