package com.example.reminder_data.remote.dto.request

@kotlinx.serialization.Serializable
data class AddMedicineReminderRequest(
    val medicine_name: String,
    val medicine_dosage: String,
    val date_start: String,
    val date_end: String,
    val instruction: String,
    val time: String
)

// date format -> yyyy-MM-dd
// time format -> HH:mm