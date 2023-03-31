package com.example.reminder_data.di

import com.example.reminder_data.repository.ReminderRepositoryImpl
import com.example.reminder_domain.repository.ReminderRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        ReminderNetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class ReminderRepositoryModule {
    @Binds
    abstract fun provideRepository(repository: ReminderRepositoryImpl): ReminderRepository
}