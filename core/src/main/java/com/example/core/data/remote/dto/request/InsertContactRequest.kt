package com.example.core.data.remote.dto.request

@kotlinx.serialization.Serializable
data class InsertContactRequest(
    val contact: String,
    val name: String
)
