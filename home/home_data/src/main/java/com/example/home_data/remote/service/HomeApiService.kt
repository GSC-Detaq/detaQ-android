package com.example.home_data.remote.service

import com.example.home_data.remote.dto.response.NotificationCountResponse

interface HomeApiService {
    suspend fun getNotificationCount(): NotificationCountResponse
}