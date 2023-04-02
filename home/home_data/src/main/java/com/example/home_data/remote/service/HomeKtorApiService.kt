package com.example.home_data.remote.service

import com.example.home_data.BuildConfig
import com.example.home_data.remote.dto.response.NotificationCountResponse
import com.example.home_data.remote.dto.response.NotificationResponse
import com.example.home_data.remote.dto.response.UpdateNotificationStatusResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class HomeKtorApiService(
    private val client: HttpClient
): HomeApiService {
    override suspend fun getNotifications(page: Int): NotificationResponse {
        val result = client.get {
            url(GET_NOTIFICATIONS + "page=$page")
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun getNotificationCount(): NotificationCountResponse {
        val result = client.get {
            url(GET_NOTIFICATION_COUNT)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun updateNotificationStatus(
        notificationId: String
    ): UpdateNotificationStatusResponse {
        val result = client.get {
            url(UPDATE_NOTIFICATION_STATUS + "notification_id=$notificationId")
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    companion object {
        private const val BASE_URL = "http://${BuildConfig.BASE_URL}"
        private const val GET_NOTIFICATIONS = "$BASE_URL/notif/getall?"
        private const val GET_NOTIFICATION_COUNT = "$BASE_URL/notif/getactive"
        private const val UPDATE_NOTIFICATION_STATUS = "$BASE_URL/notif/updateclickstatus?"
    }
}