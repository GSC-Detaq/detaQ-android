package com.example.home_domain.repository

import com.example.core.utils.Resource

interface HomeRepository {

    suspend fun getNotificationCount(): Resource<Int>

    suspend fun updateNotificationStatus(
        notificationId: String
    ): Resource<String>
}