package com.example.reminder_domain.use_cases

import com.example.core.utils.Resource
import com.example.reminder_domain.repository.ReminderRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
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