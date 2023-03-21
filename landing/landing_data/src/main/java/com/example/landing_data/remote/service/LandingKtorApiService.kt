package com.example.landing_data.remote.service

import com.example.landing_data.BuildConfig
import com.example.landing_data.remote.dto.request.LoginRequest
import com.example.landing_data.remote.dto.request.RegisterRequest
import com.example.landing_data.remote.dto.response.LoginResponse
import com.example.landing_data.remote.dto.response.RegisterResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class LandingKtorApiService(
    private val client: HttpClient
): LandingApiService {
    override suspend fun register(request: RegisterRequest): RegisterResponse {
        val result = client.post {
            url(REGISTER_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun login(request: LoginRequest): LoginResponse {
        val result = client.post {
            url(LOGIN_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    companion object {
        private const val BASE_URL = "http://${BuildConfig.BASE_URL}"
        private const val REGISTER_URL = "$BASE_URL/user/register"
        private const val LOGIN_URL = "$BASE_URL/user/login"
    }
}