package com.example.landing_data.remote.dto.response

@kotlinx.serialization.Serializable
data class LoginResponse(
    val meta: Meta,
    val data: Data? = null
) {

    @kotlinx.serialization.Serializable
    data class Meta(
        val success: Boolean,
        val message: String
    )

    @kotlinx.serialization.Serializable
    data class Data(
        val name: String,
        val email: String,
        val token: String
    )
}
