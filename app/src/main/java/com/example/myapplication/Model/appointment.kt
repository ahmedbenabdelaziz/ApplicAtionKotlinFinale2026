package com.example.porjetmobile2025.Model

data class Appointment(
    val doctorName: String? = null,
    val patientName: String? = null,
    val date: String? = null,
    val complaint: String? = null,
    val specialization: String? = null,
    val id :String?=null ,
) {
    constructor() : this(null, null, null, null, null,null)
}
