package com.example.home_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class NotificationResponse(
    val meta: Meta,
    val page: Int,
    val data: List<Data>
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val notification_id: String,
        val title: String,
        val body: String,
        val additional_link: String,
        val timestamp: String,
        val clicked: Boolean,
        val uid: String,
        val notif_type: String,
        val lat: String,
        val long: String
    )
}