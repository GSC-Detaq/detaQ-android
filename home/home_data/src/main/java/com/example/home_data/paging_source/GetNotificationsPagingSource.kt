package com.example.home_data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.core.data.utils.ApiResponse
import com.example.core.utils.errors.CustomError
import com.example.home_data.mapper.toNotification
import com.example.home_data.remote.source.HomeRemoteDataSource
import com.example.home_domain.model.Notification

class GetNotificationsPagingSource(
    private val remoteDataSource: HomeRemoteDataSource
): PagingSource<Int, Notification>() {

    override fun getRefreshKey(state: PagingState<Int, Notification>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Notification> {
        val page = params.key ?: 1

        return try {
            val response = remoteDataSource.getNotifications(
                page = page
            )

            when (response) {
                ApiResponse.Empty -> {
                    LoadResult.Page(
                        data = emptyList(),
                        prevKey = if(page==1) null else page.minus(1),
                        nextKey = null
                    )
                }
                is ApiResponse.Error -> {
                    LoadResult.Error(
                        CustomError(response.errorMessage)
                    )
                }
                is ApiResponse.Success -> {
                    val notifications = response.data

                    val nextKey = if(notifications.isEmpty()) {
                        null
                    } else {
                        page.plus(1) //+ (params.loadSize / LOAD_SIZE)
                    }

                    LoadResult.Page(
                        data = notifications.map { it.toNotification() },
                        prevKey = if(page==1) null else page.minus(1),
                        nextKey = nextKey
                    )
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}