package com.example.core.data.mapper

import com.example.core.data.remote.dto.response.ContactData
import com.example.core.domain.model.Contact

fun ContactData.toContact(): Contact {
    return Contact(
        id = this.contact_id,
        number = this.contact,
        name = this.name
    )
}