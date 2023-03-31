package com.example.reminder_domain.model

import java.time.LocalDate

data class MedicineReminder(
    val reminderId: String,
    val medicineName: String,
    val medicineDosage: Int,
    val dates: List<LocalDate>,
    val time: List<Time>
) {
    data class Time(
        val hour: Int,
        val minute: Int,
        val afterEat: Boolean
    )
}
