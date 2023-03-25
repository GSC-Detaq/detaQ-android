package com.example.core.domain.di

import com.example.core.domain.repository.CoreRepository
import com.example.core.domain.use_cases.CoreUseCases
import com.example.core.domain.use_cases.GetContactById
import com.example.core.domain.use_cases.InsertContact
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CoreUseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideCoreUseCases(
        repository: CoreRepository
    ): CoreUseCases {
        return CoreUseCases(
            insertContact = InsertContact(repository = repository),
            getContactById = GetContactById(repository = repository)
        )
    }
}