package com.example.home_data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.core.data.utils.ApiResponse
import com.example.core.utils.Resource
import com.example.home_data.paging_source.GetNotificationsPagingSource
import com.example.home_data.remote.source.HomeRemoteDataSource
import com.example.home_domain.model.Notification
import com.example.home_domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val remoteDataSource: HomeRemoteDataSource
): HomeRepository {
    override fun getNotifications(): Flow<PagingData<Notification>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 15,
                pageSize = 15,
                enablePlaceholders = true
            ),
            pagingSourceFactory = {
                GetNotificationsPagingSource(
                    remoteDataSource = remoteDataSource
                )
            }
        ).flow
    }

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

    override suspend fun updateNotificationStatus(notificationId: String): Resource<String> {
        return when(
            val result = remoteDataSource
                .updateNotificationStatus(
                    notificationId = notificationId
                )
        ) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(
                    result.data
                )
            }
        }
    }
}