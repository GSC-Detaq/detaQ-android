package com.example.reminder_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class EndReminderResponse(
    val meta: Meta
)
