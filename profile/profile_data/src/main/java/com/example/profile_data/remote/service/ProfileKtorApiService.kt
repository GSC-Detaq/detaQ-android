package com.example.profile_data.remote.service

import com.example.profile_data.BuildConfig
import com.example.profile_data.remote.dto.response.UserResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ProfileKtorApiService(
    private val client: HttpClient
): ProfileApiService {
    override suspend fun getUserPersonal(): UserResponse {
        val result = client.get {
            url(GET_USER_PERSONAL)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    companion object {
        private const val BASE_URL = "http://${BuildConfig.BASE_URL}"
        private const val GET_USER_PERSONAL = "$BASE_URL/user/myuser"
    }
}