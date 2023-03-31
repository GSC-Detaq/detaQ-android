package com.example.core.domain.use_cases

import com.example.core.domain.repository.CoreRepository
import com.example.core.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class InsertContact @Inject constructor(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(
        number: String,
        name: String
    ): Resource<Unit> {
        return repository.insertContact(
            number = number,
            name = name
        )
    }
}