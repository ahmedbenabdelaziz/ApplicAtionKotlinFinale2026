package com.example.porjetmobile2025.Controller

import android.widget.Toast
import com.example.porjetmobile2025.Model.Appointment
import com.google.firebase.database.*

class MyAppointmentsController(private val database: DatabaseReference) {

    fun fetchAppointments(appointmentList: MutableList<Appointment>, callback: () -> Unit) {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                appointmentList.clear()
                for (appointmentSnapshot in snapshot.children) {
                    val appointment = appointmentSnapshot.getValue(Appointment::class.java)
                    if (appointment != null) {
                        appointmentList.add(appointment)
                    }
                }
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    fun deleteAppointment(doctorName: String, callback: (Boolean) -> Unit) {
        database.orderByChild("doctorName").equalTo(doctorName)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (appointmentSnapshot in snapshot.children) {
                        appointmentSnapshot.ref.removeValue()
                    }
                    callback(true)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false)
                }
            })
    }
}
