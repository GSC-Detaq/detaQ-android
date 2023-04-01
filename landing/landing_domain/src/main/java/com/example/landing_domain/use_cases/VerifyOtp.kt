package com.example.landing_domain.use_cases

import com.example.core.utils.Resource
import com.example.landing_domain.repository.LandingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class VerifyOtp @Inject constructor(
    private val repository: LandingRepository
){
    operator fun invoke(
        verificationId: String,
        otp: String
    ): Flow<Resource<Unit>> {
        return repository.verifyOtp(
            verificationId = verificationId,
            otp = otp
        )
    }
}