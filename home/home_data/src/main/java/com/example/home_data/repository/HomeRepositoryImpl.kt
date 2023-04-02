package com.example.home_data.repository

import com.example.core.data.utils.ApiResponse
import com.example.core.utils.Resource
import com.example.home_data.remote.source.HomeRemoteDataSource
import com.example.home_domain.repository.HomeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource
): HomeRepository {
    override suspend fun getNotificationCount(): Resource<Int> {
        return when(val result = remoteDataSource.getNotificationCount()) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(
                    result.data.active_notif_count
                )
            }
        }
    }
}