package com.example.sos_domain.di

import com.example.sos_domain.repository.SosRepository
import com.example.sos_domain.use_cases.AddSosNotification
import com.example.sos_domain.use_cases.SendSos
import com.example.sos_domain.use_cases.SosUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object SosUseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideSosUseCases(
        repository: SosRepository
    ): SosUseCases {
        return SosUseCases(
            sendSos = SendSos(
                repository = repository
            ),
            addSosNotification = AddSosNotification(
                repository = repository
            )
        )
    }
}