package com.example.home_data.remote.service

import com.example.home_data.remote.dto.response.NotificationCountResponse
import com.example.home_data.remote.dto.response.NotificationResponse
import com.example.home_data.remote.dto.response.UpdateNotificationStatusResponse

interface HomeApiService {
    suspend fun getNotifications(
        page: Int
    ): NotificationResponse

    suspend fun getNotificationCount(): NotificationCountResponse

    suspend fun updateNotificationStatus(
        notificationId: String
    ): UpdateNotificationStatusResponse
}