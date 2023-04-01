package com.example.profile_domain.use_cases

import com.example.core.utils.Resource
import com.example.profile_domain.repository.ProfileRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ConnectWristband @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(
        code: String
    ): Resource<String> {
        return repository.connectWristband(
            code = code
        )
    }
}