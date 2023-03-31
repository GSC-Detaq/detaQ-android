package com.example.sos_data.remote.service

import com.example.sos_data.BuildConfig
import com.example.sos_data.remote.dto.request.SendSosRequest
import com.example.sos_data.remote.dto.response.SendSosResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class SosKtorApiService(
    private val client: HttpClient
): SosApiService {
    override suspend fun sendSos(request: SendSosRequest): SendSosResponse {
        val result = client.post {
            url(SEND_SOS_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    companion object {
        private const val BASE_URL = "http://${BuildConfig.BASE_URL}"
        private const val SEND_SOS_URL = "$BASE_URL/fcm/sendnotif"
    }
}