package com.example.home_data.di

import com.example.home_data.repository.HomeRepositoryImpl
import com.example.home_domain.repository.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        HomeNetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class HomeRepositoryModule {
    @Binds
    abstract fun provideRepository(repository: HomeRepositoryImpl): HomeRepository
}