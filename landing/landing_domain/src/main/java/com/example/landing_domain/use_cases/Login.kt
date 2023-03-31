package com.example.landing_domain.use_cases

import com.example.core.utils.Resource
import com.example.landing_domain.repository.LandingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Login @Inject constructor(
    private val repository: LandingRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): Resource<Unit> {
        return repository.login(
            email = email,
            password = password
        )
    }
}