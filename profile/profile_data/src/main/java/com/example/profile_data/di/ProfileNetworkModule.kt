package com.example.profile_data.di

import com.example.profile_data.remote.service.ProfileApiService
import com.example.profile_data.remote.service.ProfileKtorApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProfileNetworkModule {

    @Singleton
    @Provides
    fun provideKtorLandingApiService(
        client: HttpClient
    ): ProfileApiService = ProfileKtorApiService(
        client = client
    )
}