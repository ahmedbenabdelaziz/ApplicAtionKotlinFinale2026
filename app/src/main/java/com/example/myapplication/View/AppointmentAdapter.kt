package com.example.porjetmobile2025.View

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.porjetmobile2025.R
import com.example.porjetmobile2025.Model.Appointment
import com.google.firebase.database.FirebaseDatabase

class AppointmentAdapter(
    private val appointmentList: MutableList<Appointment>,
    private val onDeleteClicked: (String) -> Unit
) : RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.appointment_item, parent, false)
        return AppointmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val appointment = appointmentList[position]
        holder.doctorName.text = appointment.doctorName
        holder.specialization.text = appointment.specialization
        holder.date.text = appointment.date

        // Gérer le clic sur le bouton "Supprimer"
        holder.deleteButton.setOnClickListener {
            appointment.doctorName?.let { p1 -> onDeleteClicked(p1) } // Appeler le callback avec le nom du docteur
        }
    }

    override fun getItemCount(): Int = appointmentList.size

    class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val doctorName: TextView = itemView.findViewById(R.id.tvDoctorName)
        val specialization: TextView = itemView.findViewById(R.id.tvSpecialization)
        val date: TextView = itemView.findViewById(R.id.tvDate)
        val deleteButton: Button = itemView.findViewById(R.id.btnDeleteAppointment) // Bouton de suppression
    }
}
