package com.example.core.data.remote.dto.response

@kotlinx.serialization.Serializable
data class ContactData(
    val contact_id: String,
    val contact: String,
    val name: String
)