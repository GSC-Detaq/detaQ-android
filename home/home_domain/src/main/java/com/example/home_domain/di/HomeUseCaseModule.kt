package com.example.home_domain.di

import com.example.home_domain.repository.HomeRepository
import com.example.home_domain.use_cases.GetNotificationCount
import com.example.home_domain.use_cases.GetNotifications
import com.example.home_domain.use_cases.HomeUseCases
import com.example.home_domain.use_cases.UpdateNotificationStatus
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object HomeUseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideHomeUseCases(
        repository: HomeRepository
    ): HomeUseCases {
        return HomeUseCases(
            getNotifications = GetNotifications(
                repository = repository
            ),
            getNotificationCount = GetNotificationCount(
                repository = repository
            ),
            updateNotificationStatus = UpdateNotificationStatus(
                repository = repository
            )
        )
    }
}