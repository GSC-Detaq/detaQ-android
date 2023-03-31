package com.example.history_data.di

import com.example.history_data.repository.HistoryRepositoryImpl
import com.example.history_domain.repository.HistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        HistoryNetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class HistoryRepositoryModule {
    @Binds
    abstract fun provideRepository(
        repository: HistoryRepositoryImpl
    ): HistoryRepository
}