package com.example.sos_data.remote.dto.request

@kotlinx.serialization.Serializable
data class AddSosNotificationRequest(
    val title: String,
    val body: String,
    val timestamp: String,
    val lat: String,
    val long: String
)
