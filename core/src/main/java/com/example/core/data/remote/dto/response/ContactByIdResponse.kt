package com.example.core.data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class ContactByIdResponse(
    val meta: Meta,
    val data: ContactData
)