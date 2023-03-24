package com.example.reminder_presenter.reminder.doctor

import com.example.reminder_presenter.model.Time
import java.time.LocalDate

data class DoctorSectionState(
    val doctors: List<Doctor> = dummyDoctors,
    val addDoctorState: AddDoctorState = AddDoctorState()
)

private val dummyDoctors = listOf(
    Doctor(
        name = "Dr. Aisyah Jamal",
        activity = "Monthly Control",
        date = LocalDate.now()
    ),
    Doctor(
        name = "Dr. Budi",
        activity = "Monthly Control",
        date = LocalDate.now()
    )
)

data class Doctor(
    val name: String,
    val activity: String,
    val date: LocalDate
)

data class AddDoctorState(
    val activity: String = "",
    val doctorName: String = "",
    val date: LocalDate = LocalDate.now(),
    val time: Time = Time(hour = 0, minute = 0)
)