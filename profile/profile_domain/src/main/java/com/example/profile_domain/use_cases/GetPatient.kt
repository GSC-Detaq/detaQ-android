package com.example.profile_domain.use_cases

import com.example.core.utils.Resource
import com.example.profile_domain.model.Family
import com.example.profile_domain.repository.ProfileRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetPatient @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(): Resource<List<Family>> {
        return repository.getPatient()
    }
}