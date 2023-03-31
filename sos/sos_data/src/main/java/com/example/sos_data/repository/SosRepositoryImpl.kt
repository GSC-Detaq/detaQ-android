package com.example.sos_data.repository

import com.example.core.data.utils.ApiResponse
import com.example.core.utils.Resource
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
            longitude = long
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
}