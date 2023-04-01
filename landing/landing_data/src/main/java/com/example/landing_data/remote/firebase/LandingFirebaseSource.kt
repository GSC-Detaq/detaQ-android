package com.example.landing_data.remote.firebase

import com.example.core.utils.Resource
import com.example.landing_domain.model.OtpResult
import kotlinx.coroutines.flow.Flow

interface LandingFirebaseSource {
    fun sendOtp(
        number: String
    ): Flow<Resource<OtpResult>>

    fun verifyOtp(
        verificationId: String,
        otp: String
    ): Flow<Resource<Unit>>
}