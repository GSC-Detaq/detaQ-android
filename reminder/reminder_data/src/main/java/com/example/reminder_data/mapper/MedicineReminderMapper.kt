package com.example.reminder_data.mapper

import com.example.core.utils.extensions.toLocalDate
import com.example.reminder_data.remote.dto.response.MedicineReminderResponse
import com.example.reminder_domain.model.MedicineReminder
import com.example.reminder_domain.utils.rangeOfDates

fun MedicineReminderResponse.Data.toMedicineReminder(): MedicineReminder {
    val start = this.date_start.toLocalDate()
    val end = this.date_end.toLocalDate()
    val listOfDates = rangeOfDates(start, end)

    val times = this.time.split(", ")

    val listOfTimes = times.map { time ->
        val hour = time.substringBefore(":").removePrefix("0").toIntOrNull() ?: 0
        val minute = time.substringAfter(":").removePrefix("0").toIntOrNull() ?: 0

        MedicineReminder.Time(
            hour = hour,
            minute = minute,
            afterEat = this.instruction == "1"
        )
    }

    return MedicineReminder(
        reminderId = this.reminder_id,
        medicineName = this.medicine_name,
        medicineDosage = this.medicine_dosage.toIntOrNull() ?: 1,
        dates = listOfDates,
        time = listOfTimes
    )
}