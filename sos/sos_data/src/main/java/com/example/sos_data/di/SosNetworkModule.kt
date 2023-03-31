package com.example.sos_data.di

import com.example.sos_data.remote.service.SosApiService
import com.example.sos_data.remote.service.SosKtorApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SosNetworkModule {

    @Singleton
    @Provides
    fun provideKtorSosApiService(
        client: HttpClient
    ): SosApiService = SosKtorApiService(
        client = client
    )
}