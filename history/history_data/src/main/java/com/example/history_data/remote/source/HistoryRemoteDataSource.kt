package com.example.history_data.remote.source

import com.example.history_data.remote.firebase.HistoryFirebaseSource
import com.example.history_domain.model.HeartBpm
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRemoteDataSource @Inject constructor(
    private val firebaseSource: HistoryFirebaseSource
) {
   fun getBpm(): Flow<HeartBpm> {
       return firebaseSource
           .getBpm()
   }
}