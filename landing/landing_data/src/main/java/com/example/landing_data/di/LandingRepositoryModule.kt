package com.example.landing_data.di

import com.example.landing_data.repository.LandingRepositoryImpl
import com.example.landing_domain.repository.LandingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        LandingNetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class LandingRepositoryModule {
    @Binds
    abstract fun provideRepository(repository: LandingRepositoryImpl): LandingRepository
}