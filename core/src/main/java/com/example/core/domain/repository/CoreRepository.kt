package com.example.core.domain.repository

import com.example.core.domain.model.Contact
import com.example.core.utils.Resource

interface CoreRepository {
    suspend fun insertContact(
        number: String,
        name: String
    ): Resource<Unit>

    suspend fun getContactById(
        id: String
    ): Resource<Contact>

    suspend fun getContacts(): Resource<List<Contact>>

    suspend fun updateFcmToken(
        token: String
    ): Resource<Unit>
}