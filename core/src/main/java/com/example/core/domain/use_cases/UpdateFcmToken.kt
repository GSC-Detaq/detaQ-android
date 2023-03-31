package com.example.core.domain.use_cases

import com.example.core.domain.repository.CoreRepository
import com.example.core.utils.Resource
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Inject

@ServiceScoped
class UpdateFcmToken @Inject constructor(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(
        token: String
    ): Resource<Unit> {
        return repository.updateFcmToken(
            token = token
        )
    }
}