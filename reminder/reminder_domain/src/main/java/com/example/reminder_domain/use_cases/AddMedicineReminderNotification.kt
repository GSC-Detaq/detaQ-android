package com.example.reminder_domain.use_cases

import com.example.core.utils.Resource
import com.example.reminder_domain.repository.ReminderRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddMedicineReminderNotification @Inject constructor(
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(
        title: String,
        body: String
    ): Resource<String> {
        return repository.addMedicineReminderNotification(
            title = title,
            body = body
        )
    }
}