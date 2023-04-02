package com.example.home_domain.repository

import androidx.paging.PagingData
import com.example.core.utils.Resource
import com.example.home_domain.model.Notification
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getNotifications(): Flow<PagingData<Notification>>

    suspend fun getNotificationCount(): Resource<Int>

    suspend fun updateNotificationStatus(
        notificationId: String
    ): Resource<String>
}