package com.example.reminder_data.remote.dto.request

@kotlinx.serialization.Serializable
data class AddDoctorReminderRequest(
    val activity: String,
    val doctor_name: String,
    val date: String,
    val time: String
)
