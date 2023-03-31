package com.example.history_data.remote.firebase

import com.example.history_domain.model.HeartBpm
import kotlinx.coroutines.flow.Flow

interface HistoryFirebaseSource {

    fun getBpm(): Flow<HeartBpm>
}