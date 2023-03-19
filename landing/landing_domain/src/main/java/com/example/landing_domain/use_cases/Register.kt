package com.example.landing_domain.use_cases

import com.example.core.utils.Resource
import com.example.landing_domain.repository.LandingRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class Register @Inject constructor(
    private val repository: LandingRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        name: String,
        roleId: Int
    ): Resource<Unit> {
        return repository.register(
            email = email,
            password = password,
            name = name,
            roleId = roleId
        )
    }
}