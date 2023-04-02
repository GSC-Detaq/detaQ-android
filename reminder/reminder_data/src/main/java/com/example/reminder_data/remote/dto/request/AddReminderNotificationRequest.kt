package com.example.reminder_data.remote.dto.request

@kotlinx.serialization.Serializable
data class AddReminderNotificationRequest(
    val title: String,
    val body: String,
    val timestamp: String
)
