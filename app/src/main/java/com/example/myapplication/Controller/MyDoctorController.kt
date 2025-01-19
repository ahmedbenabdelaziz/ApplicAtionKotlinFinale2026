package com.example.porjetmobile2025.Controller

import android.content.Context
import android.content.Intent
import com.example.porjetmobile2025.Model.Doctor
import com.example.porjetmobile2025.View.AppointmentActivity

class MyDoctorController {

    fun navigateToAppointment(context: Context, doctor: Doctor) {
        val intent = Intent(context, AppointmentActivity::class.java).apply {
            putExtra("doctor_name", doctor.name)
            putExtra("specialization", doctor.specialization)
            putExtra("address", doctor.address)
        }
        context.startActivity(intent)
    }
}
