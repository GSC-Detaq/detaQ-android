package com.example.reminder_presenter.reminder.doctor

import java.util.*

sealed class DoctorSectionEvent {
    object AddDoctor: DoctorSectionEvent()
    data class OnActivityChange(val activity: String): DoctorSectionEvent()
    data class OnDoctorNameChange(val name: String): DoctorSectionEvent()
    data class OnPickDate(val date: Date): DoctorSectionEvent()
    data class OnPickTime(
        val hour: Int,
        val minute: Int
    ): DoctorSectionEvent()
}