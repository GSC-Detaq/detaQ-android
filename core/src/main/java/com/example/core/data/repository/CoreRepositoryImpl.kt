package com.example.core.data.repository

import com.example.core.data.mapper.toContact
import com.example.core.data.remote.dto.request.InsertContactRequest
import com.example.core.data.remote.source.CoreRemoteDataSource
import com.example.core.data.utils.ApiResponse
import com.example.core.domain.model.Contact
import com.example.core.domain.repository.CoreRepository
import com.example.core.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoreRepositoryImpl @Inject constructor(
    private val remoteDataSource: CoreRemoteDataSource
): CoreRepository {
    override suspend fun insertContact(
        number: String,
        name: String
    ): Resource<Unit> {
        val request = InsertContactRequest(
            contact = number,
            name = name
        )

        return when(val result = remoteDataSource.insertContact(request)) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(Unit)
            }
        }
    }

    override suspend fun getContactById(id: String): Resource<Contact> {
        return when(val result = remoteDataSource.getContactById(id)) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(
                    result.data.toContact()
                )
            }
        }
    }
}