package com.example.history_domain.repository

import com.example.history_domain.model.HeartBpm
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getBpm(): Flow<HeartBpm>
}