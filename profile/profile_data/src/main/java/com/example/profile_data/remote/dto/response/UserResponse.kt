package com.example.profile_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class UserResponse(
    val meta: Meta,
    val data: Data
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val email: String,
        val name: String,
        val role_name: String
    )
}
