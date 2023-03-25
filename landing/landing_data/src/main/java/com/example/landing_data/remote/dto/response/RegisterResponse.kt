package com.example.landing_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class RegisterResponse(
    val meta: Meta,
    val data: Data? = null
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val email: String,
        val token: String
    )
}
