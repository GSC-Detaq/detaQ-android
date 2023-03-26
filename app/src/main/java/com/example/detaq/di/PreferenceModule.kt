package com.example.detaq.di

import com.example.core.data.preferences.DefaultPreferences
import com.example.core.data.preferences.EncTokenPreferences
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.preferences.TokenPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferenceModule {

    @Binds
    abstract fun providePreferences(
        preferences: DefaultPreferences
    ): Preferences

    @Binds
    abstract fun provideTokenPreferences(
        preferences: EncTokenPreferences
    ): TokenPreferences
    
}