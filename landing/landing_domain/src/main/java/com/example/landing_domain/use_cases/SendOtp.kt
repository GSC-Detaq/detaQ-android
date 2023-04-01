package com.example.landing_domain.use_cases

import com.example.core.utils.Resource
import com.example.landing_domain.model.OtpResult
import com.example.landing_domain.repository.LandingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class SendOtp @Inject constructor(
    private val repository: LandingRepository
){
    operator fun invoke(
        number: String
    ): Flow<Resource<OtpResult>> {
        return repository.sendOtp(
            number = number
        )
    }
}