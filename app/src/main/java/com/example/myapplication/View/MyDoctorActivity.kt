package com.example.porjetmobile2025.View

import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.porjetmobile2025.Controller.MyDoctorController
import com.example.porjetmobile2025.Model.Doctor
import com.example.porjetmobile2025.R

class MyDoctorActivity : AppCompatActivity() {

    private lateinit var myDoctorController: MyDoctorController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchdoctor)

        myDoctorController = MyDoctorController()

        val btnAppointment1 = findViewById<Button>(R.id.btn_appointment_1)
        val btnAppointment2 = findViewById<Button>(R.id.btn_appointment_2)
        val btnAppointment3 = findViewById<Button>(R.id.btn_appointment_3)
        val btnAppointment4 = findViewById<Button>(R.id.btn_appointment_4)

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

        val doctor1 = Doctor("Dr. Ahmed Abdelaziz", "Cardiologist", "Hopital Monastir, Esprim")
        val doctor2 = Doctor("Dr. Fathi Harrabi", "Neurologist", "Clinic Rahma, Mahdia")
        val doctor3 = Doctor("Dr. Salma Issa", "Dermatologist", "Salakta, Sousse")
        val doctor4 = Doctor("Dr. Mohamed Boura", "Dermatologist", "Beja Center, Esprim")

        btnAppointment1.setOnClickListener {
            myDoctorController.navigateToAppointment(this, doctor1)
        }

        btnAppointment2.setOnClickListener {
            myDoctorController.navigateToAppointment(this, doctor2)
        }

        btnAppointment3.setOnClickListener {
            myDoctorController.navigateToAppointment(this, doctor3)
        }

        btnAppointment4.setOnClickListener {
            myDoctorController.navigateToAppointment(this, doctor4)
        }
    }
}
