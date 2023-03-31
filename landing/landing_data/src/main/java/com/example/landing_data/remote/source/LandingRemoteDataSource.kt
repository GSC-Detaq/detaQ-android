package com.example.landing_data.remote.source

import com.example.core.data.remote.utils.tryCatch
import com.example.core.data.utils.ApiResponse
import com.example.core.domain.dispatchers.DispatchersProvider
import com.example.landing_data.remote.dto.request.LoginRequest
import com.example.landing_data.remote.dto.request.RegisterRequest
import com.example.landing_data.remote.dto.response.LoginResponse
import com.example.landing_data.remote.dto.response.RegisterResponse
import com.example.landing_data.remote.service.LandingApiService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LandingRemoteDataSource @Inject constructor(
    private val apiService: LandingApiService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun register(
        request: RegisterRequest
    ): ApiResponse<RegisterResponse.Data> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.register(request)

                if (result.meta.success) {
                    result.data?.let { data ->
                        ApiResponse.Success(data)
                    } ?: ApiResponse.Error("data null")
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun login(
        request: LoginRequest
    ): ApiResponse<LoginResponse.Data> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.login(request)

                if (result.meta.success) {
                    result.data?.let { data ->
                        ApiResponse.Success(data)
                    } ?: ApiResponse.Error("data null")
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }
}