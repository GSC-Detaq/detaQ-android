package com.example.sos_domain.use_cases

import com.example.core.utils.Resource
import com.example.sos_domain.repository.SosRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SendSos @Inject constructor(
    private val repository: SosRepository
) {
    suspend operator fun invoke(
        lat: Double,
        long: Double
    ): Resource<String> {
        return repository.sendSos(
            lat = lat.toString(),
            long = long.toString()
        )
    }
}