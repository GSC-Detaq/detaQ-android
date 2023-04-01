package com.example.home_data.di

import com.example.home_data.remote.service.HomeApiService
import com.example.home_data.remote.service.HomeKtorApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeNetworkModule {

    @Singleton
    @Provides
    fun provideKtorHomeApiService(
        client: HttpClient
    ): HomeApiService = HomeKtorApiService(
        client = client
    )
}