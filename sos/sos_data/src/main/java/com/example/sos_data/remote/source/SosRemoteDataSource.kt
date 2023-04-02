package com.example.sos_data.remote.source

import com.example.core.data.remote.utils.tryCatch
import com.example.core.data.utils.ApiResponse
import com.example.core.domain.dispatchers.DispatchersProvider
import com.example.sos_data.remote.dto.request.AddSosNotificationRequest
import com.example.sos_data.remote.dto.request.SendSosRequest
import com.example.sos_data.remote.service.SosApiService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SosRemoteDataSource @Inject constructor(
    private val apiService: SosApiService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun sendSos(
        request: SendSosRequest
    ): ApiResponse<String> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.sendSos(request)

                if (result.meta.success) {
                    ApiResponse.Success(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun addSosNotification(
        request: AddSosNotificationRequest
    ): ApiResponse<String> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.addSosNotification(request)

                if (result.meta.success) {
                    ApiResponse.Success(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }
}