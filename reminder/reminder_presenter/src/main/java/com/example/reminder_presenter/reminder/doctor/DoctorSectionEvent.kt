package com.example.reminder_presenter.reminder.doctor

import com.example.core.domain.model.Time
import java.time.LocalDate

sealed class DoctorSectionEvent {
    object AddDoctor: DoctorSectionEvent()
    data class OnActivityChange(val activity: String): DoctorSectionEvent()
    data class OnDoctorNameChange(val name: String): DoctorSectionEvent()
    data class OnPickDate(val date: LocalDate): DoctorSectionEvent()
    data class OnPickTime(
        val time: Time
    ): DoctorSectionEvent()

    object ResetAddState: DoctorSectionEvent()
}