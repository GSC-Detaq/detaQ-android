package com.example.reminder_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class MedicineReminderResponse(
    val meta: Meta,
    val data: List<Data>
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val reminder_id: String,
        val medicine_name: String,
        val medicine_dosage: String,
        val date_start: String,
        val date_end: String,
        val time: String,
        val instruction: String
    )
}