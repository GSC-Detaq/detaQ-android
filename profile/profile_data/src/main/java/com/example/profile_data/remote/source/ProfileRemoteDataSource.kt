package com.example.profile_data.remote.source

import com.example.core.data.remote.utils.tryCatch
import com.example.core.data.utils.ApiResponse
import com.example.core.domain.dispatchers.DispatchersProvider
import com.example.profile_data.remote.dto.response.UserResponse
import com.example.profile_data.remote.service.ProfileApiService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRemoteDataSource @Inject constructor(
    private val apiService: ProfileApiService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun getUserPersonal(): ApiResponse<UserResponse.Data> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getUserPersonal()

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