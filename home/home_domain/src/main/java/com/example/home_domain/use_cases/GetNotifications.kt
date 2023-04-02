package com.example.home_domain.use_cases

import androidx.paging.PagingData
import com.example.home_domain.model.Notification
import com.example.home_domain.repository.HomeRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetNotifications @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<PagingData<Notification>> {
        return repository.getNotifications()
    }
}