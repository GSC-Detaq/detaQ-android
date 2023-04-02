package com.example.home_data.remote.service

import com.example.home_data.BuildConfig
import com.example.home_data.remote.dto.response.NotificationCountResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class HomeKtorApiService(
    private val client: HttpClient
): HomeApiService {
    override suspend fun getNotificationCount(): NotificationCountResponse {
        val result = client.get {
            url(GET_NOTIFICATION_COUNT)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    companion object {
        private const val BASE_URL = "http://${BuildConfig.BASE_URL}"
        private const val GET_NOTIFICATION_COUNT = "$BASE_URL/notif/getactive"
    }
}