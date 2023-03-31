package com.example.profile_domain.use_cases

import com.example.core.utils.Resource
import com.example.profile_domain.model.User
import com.example.profile_domain.repository.ProfileRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetUserPersonal @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Resource<User> {
        return repository.getUserPersonal()
    }
}