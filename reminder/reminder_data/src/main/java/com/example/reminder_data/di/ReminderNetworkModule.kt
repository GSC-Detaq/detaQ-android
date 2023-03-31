package com.example.reminder_data.di

import com.example.reminder_data.remote.service.ReminderApiService
import com.example.reminder_data.remote.service.ReminderKtorApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReminderNetworkModule {

    @Singleton
    @Provides
    fun provideKtorReminderApiService(
        client: HttpClient
    ): ReminderApiService = ReminderKtorApiService(
        client = client
    )
}