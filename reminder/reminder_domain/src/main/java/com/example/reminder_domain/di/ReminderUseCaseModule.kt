package com.example.reminder_domain.di

import com.example.reminder_domain.repository.ReminderRepository
import com.example.reminder_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ReminderUseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideReminderUseCases(
        repository: ReminderRepository
    ): ReminderUseCases {
        return ReminderUseCases(
            addMedicineReminder = AddMedicineReminder(
                repository = repository
            ),
            getMedicineReminders = GetMedicineReminders(
                repository = repository
            ),
            addMedicineReminderNotification = AddMedicineReminderNotification(
                repository = repository
            ),
            endMedicineReminder = EndMedicineReminder(
                repository = repository
            ),
            addDoctorReminder = AddDoctorReminder(
                repository = repository
            ),
            getDoctorReminders = GetDoctorReminders(
                repository = repository
            ),
            addDoctorReminderNotification = AddDoctorReminderNotification(
                repository = repository
            ),
            endDoctorReminder = EndDoctorReminder(
                repository = repository
            )
        )
    }
}