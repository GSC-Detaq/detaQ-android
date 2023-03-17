package com.example.landing_data.remote.dto.request

@kotlinx.serialization.Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
