package com.example.landing_data.repository

import com.example.core.data.remote.dto.request.UpdateFcmTokenRequest
import com.example.core.data.remote.source.CoreRemoteDataSource
import com.example.core.data.utils.ApiResponse
import com.example.core.domain.preferences.TokenPreferences
import com.example.core.utils.Resource
import com.example.landing_data.remote.dto.request.LoginRequest
import com.example.landing_data.remote.dto.request.RegisterRequest
import com.example.landing_data.remote.source.LandingRemoteDataSource
import com.example.landing_domain.repository.LandingRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LandingRepositoryImpl @Inject constructor(
    private val remoteDataSource: LandingRemoteDataSource,
    private val coreRemoteDataSource: CoreRemoteDataSource,
    private val tokenPreferences: TokenPreferences
): LandingRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Resource<Unit> {
        val request = LoginRequest(
            email = email,
            password = password
        )

        return when(val result = remoteDataSource.login(request)) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                tokenPreferences.setToken(
                    token = result.data.token
                )

                coreRemoteDataSource.updateFcmToken(
                    request = UpdateFcmTokenRequest(
                        fcm_token = tokenPreferences.getFcmToken()
                    )
                )

                Resource.Success(Unit)
            }
        }
    }

    override suspend fun register(
        email: String,
        password: String,
        name: String,
        roleId: Int,
    ): Resource<Unit> {
        val request = RegisterRequest(
            email = email,
            password = password,
            name = name,
            role_id = roleId
        )

        return when(val result = remoteDataSource.register(request)) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                tokenPreferences.setToken(
                    token = result.data.token
                )

                coreRemoteDataSource.updateFcmToken(
                    request = UpdateFcmTokenRequest(
                        fcm_token = tokenPreferences.getFcmToken()
                    )
                )

                Resource.Success(Unit)
            }
        }
    }
}