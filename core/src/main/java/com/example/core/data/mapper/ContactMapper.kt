package com.example.core.data.mapper

import com.example.core.data.remote.dto.response.ContactByIdResponse
import com.example.core.domain.model.Contact

fun ContactByIdResponse.Data.toContact(): Contact {
    return Contact(
        id = this.contact_id,
        number = this.contact,
        name = this.name
    )
}