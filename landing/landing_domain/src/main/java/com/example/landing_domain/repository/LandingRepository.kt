package com.example.landing_domain.repository

import com.example.core.utils.Resource
import com.example.landing_domain.model.OtpResult
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

    fun sendOtp(
        number: String
    ): Flow<Resource<OtpResult>>

    fun verifyOtp(
        verificationId: String,
        otp: String
    ): Flow<Resource<Unit>>
}