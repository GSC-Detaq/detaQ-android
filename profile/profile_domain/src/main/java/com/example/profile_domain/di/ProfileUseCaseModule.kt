package com.example.profile_domain.di

import com.example.profile_domain.repository.ProfileRepository
import com.example.profile_domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ProfileUseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideLandingUseCases(
        repository: ProfileRepository
    ): ProfileUseCases {
        return ProfileUseCases(
            getUserPersonal = GetUserPersonal(
                repository = repository
            ),
            addNewFamily = AddNewFamily(
                repository = repository
            ),
            getFamily = GetFamily(
                repository = repository
            ),
            connectWristband = ConnectWristband(
                repository = repository
            )
        )
    }
}