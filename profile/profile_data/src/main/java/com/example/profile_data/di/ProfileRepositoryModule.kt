package com.example.profile_data.di

import com.example.profile_data.repository.ProfileRepositoryImpl
import com.example.profile_domain.repository.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        ProfileNetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class ProfileRepositoryModule {
    @Binds
    abstract fun provideRepository(repository: ProfileRepositoryImpl): ProfileRepository
}