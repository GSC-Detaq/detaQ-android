package com.example.core.data.remote.service.contact

import com.example.core.data.remote.dto.request.InsertContactRequest
import com.example.core.data.remote.dto.response.ContactByIdResponse
import com.example.core.data.remote.dto.response.ContactsResponse
import com.example.core.data.remote.dto.response.InsertContactResponse

interface ContactApiService {

    suspend fun insertContact(request: InsertContactRequest): InsertContactResponse

    suspend fun getContactById(id: String): ContactByIdResponse

    suspend fun getAllContacts(): ContactsResponse
}