package com.example.sos_data.di

import com.example.sos_data.repository.SosRepositoryImpl
import com.example.sos_domain.repository.SosRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        SosNetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class SosRepositoryModule {
    @Binds
    abstract fun provideRepository(
        repository: SosRepositoryImpl
    ): SosRepository
}