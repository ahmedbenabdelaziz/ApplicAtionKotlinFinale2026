package com.example.porjetmobile2025.View

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.porjetmobile2025.Controller.MyAppointmentsController
import com.example.porjetmobile2025.Model.Appointment
import com.example.porjetmobile2025.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MyAppointmentsNotifActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var database: DatabaseReference
    private lateinit var myAppointmentsController: MyAppointmentsController
    private val appointmentList = mutableListOf<Appointment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_appointments)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        database = FirebaseDatabase.getInstance().getReference("appointments")
        myAppointmentsController = MyAppointmentsController(database)

        appointmentAdapter = AppointmentAdapter(appointmentList) { doctorName ->
            myAppointmentsController.deleteAppointment(doctorName) { success ->
                if (success) {
                    Toast.makeText(this, "Rendez-vous supprimé", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Erreur lors de la suppression", Toast.LENGTH_SHORT).show()
                }
            }
        }
        recyclerView.adapter = appointmentAdapter

        myAppointmentsController.fetchAppointments(appointmentList) {
            appointmentAdapter.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
