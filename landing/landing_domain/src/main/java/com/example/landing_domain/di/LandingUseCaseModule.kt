package com.example.landing_domain.di

import com.example.landing_domain.repository.LandingRepository
import com.example.landing_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object LandingUseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideLandingUseCases(
        repository: LandingRepository
    ): LandingUseCases {
        return LandingUseCases(
            login = Login(repository = repository),
            register = Register(repository = repository),
            verifyOtp = VerifyOtp(repository = repository),
            validateNumber = ValidateNumber(),
            validatePassword = ValidatePassword()
        )
    }
}