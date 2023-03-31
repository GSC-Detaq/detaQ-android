package com.example.profile_data.remote.service

import com.example.profile_data.remote.dto.response.UserResponse

interface ProfileApiService {
    suspend fun getUserPersonal(): UserResponse
}