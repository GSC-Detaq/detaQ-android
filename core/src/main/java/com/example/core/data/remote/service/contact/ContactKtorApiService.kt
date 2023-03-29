package com.example.core.data.remote.service.contact

import com.example.core.BuildConfig
import com.example.core.data.remote.dto.request.InsertContactRequest
import com.example.core.data.remote.dto.response.ContactByIdResponse
import com.example.core.data.remote.dto.response.ContactsResponse
import com.example.core.data.remote.dto.response.InsertContactResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ContactKtorApiService(
    private val client: HttpClient
): ContactApiService {
    override suspend fun insertContact(request: InsertContactRequest): InsertContactResponse {
        val result = client.post {
            url(INSERT_CONTACT_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun getContactById(id: String): ContactByIdResponse {
        val result = client.get {
            val url = "$CONTACT_BY_ID_URL?contact_id=$id"

            url(url)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun getAllContacts(): ContactsResponse {
        val result = client.get {
            url(ALL_CONTACT_URL)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    companion object {
        private const val BASE_URL = "http://${BuildConfig.CONTACT_BASE_URL}"
        private const val INSERT_CONTACT_URL = "$BASE_URL/emcontact/new"
        private const val CONTACT_BY_ID_URL = "$BASE_URL/emcontact/bycontactid"
        private const val ALL_CONTACT_URL = "$BASE_URL/emcontact/allemcontact"
    }
}