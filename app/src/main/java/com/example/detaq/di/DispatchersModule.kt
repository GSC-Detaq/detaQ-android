package com.example.detaq.di

import com.example.core.domain.dispatchers.DispatchersProvider
import com.example.core.utils.StandardDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatchersModule {

    @Binds
    abstract fun provideDispatchers(
        dispatchers: StandardDispatchers
    ): DispatchersProvider
    
}