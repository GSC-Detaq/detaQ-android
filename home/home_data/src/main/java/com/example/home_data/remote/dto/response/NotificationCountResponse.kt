package com.example.home_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class NotificationCountResponse(
    val meta: Meta,
    val data: Data
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val active_notif_count: Int
    )
}