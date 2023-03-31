package com.example.core.data.di

import com.example.core.data.repository.CoreRepositoryImpl
import com.example.core.domain.repository.CoreRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        CoreNetworkModule::class
    ]
)
@InstallIn(SingletonComponent::class)
abstract class CoreRepositoryModule {
    @Binds
    abstract fun provideRepository(repository: CoreRepositoryImpl): CoreRepository
}