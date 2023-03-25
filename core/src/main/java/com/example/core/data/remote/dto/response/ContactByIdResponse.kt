package com.example.core.data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class ContactByIdResponse(
    val meta: Meta,
    val data: Data
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val contact_id: String,
        val contact: String,
        val name: String
    )
}