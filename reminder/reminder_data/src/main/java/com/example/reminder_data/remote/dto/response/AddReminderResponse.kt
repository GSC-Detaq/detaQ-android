package com.example.reminder_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class AddReminderResponse(
    val meta: Meta,
    val data: Data
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val created_id_related: String
    )
}
