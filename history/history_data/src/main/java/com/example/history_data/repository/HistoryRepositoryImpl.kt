package com.example.history_data.repository

import com.example.history_data.remote.source.HistoryRemoteDataSource
import com.example.history_domain.model.HeartBpm
import com.example.history_domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepositoryImpl @Inject constructor(
    private val remoteDataSource: HistoryRemoteDataSource
): HistoryRepository {
    override fun getBpm(): Flow<HeartBpm> {
        return remoteDataSource.getBpm()
    }
}