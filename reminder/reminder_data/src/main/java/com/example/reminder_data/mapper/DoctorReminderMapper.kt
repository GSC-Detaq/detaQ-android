package com.example.reminder_data.mapper

import com.example.core.utils.extensions.toLocalDate
import com.example.reminder_data.remote.dto.response.DoctorReminderResponse
import com.example.reminder_domain.model.DoctorReminder
import com.example.core.domain.model.Time

fun DoctorReminderResponse.Data.toDoctorReminder(): DoctorReminder {
    val hour = time.substringBefore(":").removePrefix("0").toIntOrNull() ?: 0
    val minute = time.substringAfter(":").removePrefix("0").toIntOrNull() ?: 0

    val time = Time(
        hour = hour,
        minute = minute
    )

    return DoctorReminder(
        reminderId = this.reminder_id,
        activity = this.activity,
        doctorName = this.doctor_name,
        date = this.date.toLocalDate(),
        time = time
    )
}