package com.example.history_data.remote.firebase

import com.example.core.utils.extensions.addValueEventListenerFlow
import com.example.history_domain.model.HeartBpm
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.Flow

class HistoryFirebaseSourceImpl: HistoryFirebaseSource {
    private val databaseRef = FirebaseDatabase
        .getInstance()
        .reference
        .child("user1")

    override fun getBpm(): Flow<HeartBpm> {
        return databaseRef
            .addValueEventListenerFlow(HeartBpm::class.java)
    }
}