package com.example.landing_data.remote.service

import com.example.landing_data.remote.dto.request.LoginRequest
import com.example.landing_data.remote.dto.request.RegisterRequest
import com.example.landing_data.remote.dto.response.LoginResponse
import com.example.landing_data.remote.dto.response.RegisterResponse

interface LandingApiService {

    suspend fun register(request: RegisterRequest): RegisterResponse

    suspend fun login(request: LoginRequest): LoginResponse
}