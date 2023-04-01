package com.example.landing_domain.repository

import com.example.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LandingRepository {
    suspend fun login(
        email: String,
        password: String
    ): Resource<Unit>

    suspend fun register(
        email: String,
        password: String,
        name: String,
        roleId: Int
    ): Resource<Unit>

    fun verifyOtp(
        verificationId: String,
        otp: String
    ): Flow<Resource<Unit>>
}