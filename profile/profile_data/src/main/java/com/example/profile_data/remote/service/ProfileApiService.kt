package com.example.profile_data.remote.service

import com.example.profile_data.remote.dto.request.AddNewFamilyResponse
import com.example.profile_data.remote.dto.response.UserResponse

interface ProfileApiService {
    suspend fun getUserPersonal(): UserResponse

    suspend fun addNewFamily(
        email: String
    ): AddNewFamilyResponse
}