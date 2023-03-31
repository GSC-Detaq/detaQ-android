package com.example.core.data.remote.dto.general

@kotlinx.serialization.Serializable
data class Meta(
    val success: Boolean,
    val message: String
)