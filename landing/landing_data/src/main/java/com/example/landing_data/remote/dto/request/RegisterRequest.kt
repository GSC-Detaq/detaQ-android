package com.example.landing_data.remote.dto.request

@kotlinx.serialization.Serializable
data class RegisterRequest(
    val email: String,
    val password: String,
    val name: String,
    val role_id: Int
)
