package com.example.profile_data.repository

import com.example.core.data.utils.ApiResponse
import com.example.core.utils.Resource
import com.example.profile_data.mapper.toFamily
import com.example.profile_data.mapper.toUser
import com.example.profile_data.remote.source.ProfileRemoteDataSource
import com.example.profile_domain.model.Family
import com.example.profile_domain.model.User
import com.example.profile_domain.repository.ProfileRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProfileRemoteDataSource
): ProfileRepository {
    override suspend fun getUserPersonal(): Resource<User> {
        return when(val result = remoteDataSource.getUserPersonal()) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(
                    result.data.toUser()
                )
            }
        }
    }

    override suspend fun addNewFamily(email: String): Resource<String> {
        return when(
            val result = remoteDataSource.addNewFamily(
                email = email
            )
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

    override suspend fun getFamily(): Resource<List<Family>> {
        return when(val result = remoteDataSource.getFamily()) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(
                    result.data.map {
                        it.toFamily()
                    }
                )
            }
        }
    }

    override suspend fun getPatient(): Resource<List<Family>> {
        return when(val result = remoteDataSource.getPatient()) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(
                    result.data.map {
                        it.toFamily()
                    }
                )
            }
        }
    }

    override suspend fun connectWristband(code: String): Resource<String> {
        return when(
            val result = remoteDataSource.connectWristband(
                code = code
            )
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