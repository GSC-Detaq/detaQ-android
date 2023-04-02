package com.example.sos_domain.use_cases

import com.example.core.utils.Resource
import com.example.sos_domain.repository.SosRepository
import dagger.hilt.android.scopes.ServiceScoped
import javax.inject.Inject

@ServiceScoped
class AddSosNotification @Inject constructor(
    private val repository: SosRepository
) {
    suspend operator fun invoke(
        title: String,
        body: String,
        lat: String,
        long: String
    ): Resource<String> {
        return repository.addSosNotification(
            title = title,
            body = body,
            lat = lat,
            long = long
        )
    }
}