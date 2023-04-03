package com.example.sos_data.remote.dto.request

@kotlinx.serialization.Serializable
data class SendSosRequest(
    val latitude: String,
    val longitude: String,
    val timestamp: String
)
