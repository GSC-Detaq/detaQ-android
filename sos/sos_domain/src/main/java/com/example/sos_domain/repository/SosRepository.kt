package com.example.sos_domain.repository

import com.example.core.utils.Resource

interface SosRepository {
    suspend fun sendSos(
        lat: String,
        long: String
    ): Resource<String>

    suspend fun addSosNotification(
        title: String,
        body: String,
        lat: String,
        long: String
    ): Resource<String>
}