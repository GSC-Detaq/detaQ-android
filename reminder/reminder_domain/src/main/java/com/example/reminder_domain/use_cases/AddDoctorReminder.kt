package com.example.reminder_domain.use_cases

import com.example.core.utils.Resource
import com.example.core.utils.extensions.asString
import com.example.core.domain.model.Time
import com.example.reminder_domain.repository.ReminderRepository
import dagger.hilt.android.scopes.ViewModelScoped
import java.time.LocalDate
import javax.inject.Inject

@ViewModelScoped
class AddDoctorReminder @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(
        activity: String,
        doctorName: String,
        date: LocalDate,
        time: Time
    ): Resource<String> {
        val hour = if (time.hour < 9) "0${time.hour}" else time.hour
        val minute = if (time.minute < 9) "0${time.minute}" else time.minute
        val timeFormatted = "$hour:$minute"

        return repository.addDoctorReminder(
            activity = activity,
            doctorName = doctorName,
            date = date.asString("yyyy-MM-dd"),
            time = timeFormatted
        )
    }
}