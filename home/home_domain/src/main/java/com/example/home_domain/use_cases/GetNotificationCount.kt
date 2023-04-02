package com.example.home_domain.use_cases

import com.example.core.utils.Resource
import com.example.home_domain.repository.HomeRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetNotificationCount @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(): Resource<Int> {
        return repository.getNotificationCount()
    }
}