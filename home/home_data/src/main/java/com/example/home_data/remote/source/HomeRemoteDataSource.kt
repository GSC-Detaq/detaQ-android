package com.example.home_data.remote.source

import com.example.core.data.remote.utils.tryCatch
import com.example.core.data.utils.ApiResponse
import com.example.core.domain.dispatchers.DispatchersProvider
import com.example.home_data.remote.dto.response.NotificationCountResponse
import com.example.home_data.remote.service.HomeApiService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRemoteDataSource @Inject constructor(
    private val apiService: HomeApiService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getNotificationCount(): ApiResponse<NotificationCountResponse.Data> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getNotificationCount()

                if (result.meta.success) {
                    ApiResponse.Success(result.data)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }
}