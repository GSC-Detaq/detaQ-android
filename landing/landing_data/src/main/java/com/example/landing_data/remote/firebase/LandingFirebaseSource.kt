package com.example.landing_data.remote.firebase

import com.example.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LandingFirebaseSource {
    fun verifyOtp(
        verificationId: String,
        otp: String
    ): Flow<Resource<Unit>>
}