package com.example.reminder_domain.model

import com.example.core.domain.model.Time
import java.time.LocalDate

data class DoctorReminder(
    val reminderId: String,
    val activity: String,
    val doctorName: String,
    val date: LocalDate,
    val time: Time
)
