package com.example.history_data.di

import com.example.history_data.remote.firebase.HistoryFirebaseSource
import com.example.history_data.remote.firebase.HistoryFirebaseSourceImpl
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
object HistoryNetworkModule {

    @Singleton
    @Provides
    fun provideFirebaseSource(): HistoryFirebaseSource {
        return HistoryFirebaseSourceImpl()
    }

}