package com.example.reminder_presenter.reminder.medicine

import java.time.DayOfWeek
import java.time.LocalDate

data class MedicineSectionState(
    val medicines: List<Medicine> = dummyMedicine,
    val addMedicineState: AddMedicineState = AddMedicineState()
)

private val dummyMedicine = listOf(
    Medicine(
        name = "Morphin X",
        dosage = 2,
        dateStart = LocalDate.now(),
        dateEnd = LocalDate.now(),
        time = listOf(
            Time(
                hour = 9,
                minute = 0,
                afterEat = false
            ),
            Time(
                hour = 18,
                minute = 0,
                afterEat = true
            )
        ),
        day = listOf(
            DayOfWeek.MONDAY,
            DayOfWeek.SATURDAY
        )
    ),
    Medicine(
        name = "Morphin Y",
        dosage = 1,
        dateStart = LocalDate.now(),
        dateEnd = LocalDate.now(),
        time = listOf(
            Time(
                hour = 9,
                minute = 0,
                afterEat = false
            ),
            Time(
                hour = 18,
                minute = 0,
                afterEat = true
            )
        ),
        day = listOf(
            DayOfWeek.FRIDAY,
            DayOfWeek.WEDNESDAY
        )
    )
)

data class AddMedicineState(
    val medicineName: String = "",
    val drugDosage: String = "",
    val dateStart: LocalDate = LocalDate.now(),
    val dateEnd: LocalDate = LocalDate.now(),
    val time: List<Time> = emptyList(),
    val instructions: List<String> = emptyList()
)

data class Medicine(
    val name: String,
    val dosage: Int,
    val dateStart: LocalDate,
    val dateEnd: LocalDate,
    val time: List<Time>,
    val day: List<DayOfWeek>
)

data class Time(
    val hour: Int,
    val minute: Int,
    val afterEat: Boolean
)