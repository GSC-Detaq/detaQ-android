package com.example.sos_data.repository

import com.example.core.data.utils.ApiResponse
import com.example.core.utils.Resource
import com.example.sos_data.remote.dto.request.AddSosNotificationRequest
import com.example.sos_data.remote.dto.request.SendSosRequest
import com.example.sos_data.remote.source.SosRemoteDataSource
import com.example.sos_domain.repository.SosRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SosRepositoryImpl @Inject constructor(
    private val remoteDataSource: SosRemoteDataSource
): SosRepository{
    override suspend fun sendSos(lat: String, long: String): Resource<String> {
        val request = SendSosRequest(
            latitude = lat,
            longitude = long,
            timestamp = System.currentTimeMillis().toString(),
        )

        return when(val result = remoteDataSource.sendSos(request)) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(result.data)
            }
        }
    }

    override suspend fun addSosNotification(
        title: String,
        body: String,
        lat: String,
        long: String,
    ): Resource<String> {
        val request = AddSosNotificationRequest(
            title = title,
            body = body,
            timestamp = System.currentTimeMillis().toString(),
            lat = lat,
            long = long
        )

        return when(
            val result = remoteDataSource
                .addSosNotification(request)
        ) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(result.data)
            }
        }
    }
}