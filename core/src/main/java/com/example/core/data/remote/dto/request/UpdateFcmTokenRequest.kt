package com.example.core.data.remote.dto.request

@kotlinx.serialization.Serializable
data class UpdateFcmTokenRequest(
    val fcm_token: String
)
