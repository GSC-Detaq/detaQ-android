package com.example.landing_data.di

import com.example.landing_data.remote.firebase.LandingFirebaseSource
import com.example.landing_data.remote.firebase.LandingFirebaseSourceImpl
import com.example.landing_data.remote.service.LandingApiService
import com.example.landing_data.remote.service.LandingKtorApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LandingNetworkModule {

    @Singleton
    @Provides
    fun provideKtorLandingApiService(
        client: HttpClient
    ): LandingApiService = LandingKtorApiService(
        client = client
    )

    @Singleton
    @Provides
    fun provideFirebaseSource(): LandingFirebaseSource {
        return LandingFirebaseSourceImpl()
    }
}