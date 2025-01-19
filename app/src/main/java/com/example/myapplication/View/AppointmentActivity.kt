package com.example.porjetmobile2025.View
import android.widget.TextView
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.porjetmobile2025.Model.Appointment
import com.example.porjetmobile2025.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AppointmentActivity : AppCompatActivity() {
    private lateinit var bookAppointmentButton: Button
    private lateinit var doctorNameTextView: TextView
    private lateinit var specializationTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var database: DatabaseReference
    private lateinit var complaintEditText: EditText
    private lateinit var doctorImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.searchlabo)
        complaintEditText = findViewById(R.id.complaintEditText)

        doctorNameTextView = findViewById(R.id.doctorNameEditText)
        specializationTextView = findViewById(R.id.spec)
        addressTextView = findViewById(R.id.addressTextView)

        val doctorName = intent.getStringExtra("doctor_name")
        val specialization = intent.getStringExtra("specialization")
        val address = intent.getStringExtra("address")

        doctorNameTextView.text = doctorName ?: "No doctor name available"
        specializationTextView.text = specialization ?: "No specialization available"
        addressTextView.text = address ?: "No address available"

        val toolbar: Toolbar = findViewById(R.id.app_bar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        doctorImageView = findViewById(R.id.doctor_image_view)

        toolbar.setNavigationOnClickListener {
            finish()
        }

        bookAppointmentButton = findViewById(R.id.book_appointment_button)
        database = FirebaseDatabase.getInstance().reference

        bookAppointmentButton.setOnClickListener {
            val appointment = Appointment(
                doctorName ?: "Unknown Doctor",
                "Ahmed Ben Abdelaziz",
                "12/02/2025",
                complaintEditText.text.toString()  ,
                specialization.toString()
            )

            val appointmentId = database.child("appointments").push().key
            if (appointmentId != null) {
                database.child("appointments").child(appointmentId).setValue(appointment)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Rendez-vous réservé avec succès!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Échec de la réservation, veuillez réessayer.", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })
    }
}
