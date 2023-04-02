package com.example.home_domain.use_cases

import com.example.core.utils.Resource
import com.example.home_domain.repository.HomeRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class UpdateNotificationStatus @Inject constructor(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(
        notificationId: String
    ): Resource<String> {
        return repository
            .updateNotificationStatus(
                notificationId = notificationId
            )
    }
}