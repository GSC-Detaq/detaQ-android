package com.example.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @Provides
    fun provideKtorHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json()
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.HEADERS
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 30000L
                connectTimeoutMillis = 3000L
                socketTimeoutMillis = 15000L
            }
        }
    }

}