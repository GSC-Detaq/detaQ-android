package com.example.core.data.remote.service.fcm

import com.example.core.BuildConfig
import com.example.core.data.remote.dto.request.UpdateFcmTokenRequest
import com.example.core.data.remote.dto.response.UpdateFcmTokenResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class FcmKtorApiService(
    private val client: HttpClient
): FcmApiService {
    override suspend fun updateToken(request: UpdateFcmTokenRequest): UpdateFcmTokenResponse {
        val result = client.post {
            url(UPDATE_FCM_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    companion object {
        private const val BASE_URL = "http://${BuildConfig.CONTACT_BASE_URL}"
        private const val UPDATE_FCM_URL = "$BASE_URL/fcm/update"
    }
}