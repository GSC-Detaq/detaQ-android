package com.example.reminder_presenter.reminder.doctor

import java.util.Date

data class DoctorSectionState(
    val doctors: List<Doctor> = dummyDoctors,
    val addDoctorState: AddDoctorState = AddDoctorState()
)

private val dummyDoctors = listOf(
    Doctor(
        name = "Dr. Aisyah Jamal",
        activity = "Monthly Control",
        date = Date()
    ),
    Doctor(
        name = "Dr. Budi",
        activity = "Monthly Control",
        date = Date()
    )
)

data class Doctor(
    val name: String,
    val activity: String,
    val date: Date
)

data class AddDoctorState(
    val activity: String = "",
    val doctorName: String = "",
    val date: Date = Date(),
    val hour: Int = 0,
    val minute: Int = 0
)