package com.example.reminder_presenter.reminder.doctor

import com.example.reminder_domain.model.DoctorReminder
import com.example.core.domain.model.Time
import java.time.LocalDate

data class DoctorSectionState(
    val doctors: List<DoctorReminder> = emptyList(),
    val addDoctorState: AddDoctorState = AddDoctorState()
)

data class AddDoctorState(
    val activity: String = "",
    val doctorName: String = "",
    val date: LocalDate = LocalDate.now(),
    val time: Time = Time(hour = 0, minute = 0)
)