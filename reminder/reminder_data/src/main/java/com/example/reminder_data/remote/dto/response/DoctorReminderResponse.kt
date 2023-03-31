package com.example.reminder_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class DoctorReminderResponse(
    val meta: Meta,
    val data: List<Data>
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val reminder_id: String,
        val activity: String,
        val doctor_name: String,
        val date: String,
        val time: String
    )
}