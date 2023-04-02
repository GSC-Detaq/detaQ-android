package com.example.reminder_domain.use_cases

import com.example.core.utils.Resource
import com.example.reminder_domain.repository.ReminderRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EndDoctorReminder @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(
        reminderId: String
    ): Resource<String> {
        return repository.endDoctorReminder(
            reminderId = reminderId
        )
    }
}