package com.example.core.data.di

import com.example.core.data.remote.service.contact.ContactApiService
import com.example.core.data.remote.service.contact.ContactKtorApiService
import com.example.core.data.remote.service.fcm.FcmApiService
import com.example.core.data.remote.service.fcm.FcmKtorApiService
import com.example.core.domain.preferences.TokenPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreNetworkModule {

    @Singleton
    @Provides
    fun provideKtorHttpClient(
        preferences: TokenPreferences
    ): HttpClient {
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

            install(Auth) {
                bearer {
                    loadTokens {
                        BearerTokens(
                            accessToken = preferences.getToken(),
                            refreshToken = ""
                        )
                    }

                    sendWithoutRequest {
                        !it.url.encodedPath.startsWith("/user/register") &&
                                !it.url.encodedPath.startsWith("/user/login")
                    }
                }
            }
        }
    }

    @Singleton
    @Provides
    fun provideKtorContactApiService(
        client: HttpClient
    ): ContactApiService = ContactKtorApiService(
        client = client
    )

    @Singleton
    @Provides
    fun provideKtorFcmApiService(
        client: HttpClient
    ): FcmApiService = FcmKtorApiService(
        client = client
    )

}