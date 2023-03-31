package com.example.history_domain.use_cases

import com.example.history_domain.model.HeartBpm
import com.example.history_domain.repository.HistoryRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetBpm @Inject constructor(
    private val repository: HistoryRepository
) {
    operator fun invoke(): Flow<HeartBpm> {
        return repository.getBpm()
    }
}