package com.example.core.domain.use_cases

import com.example.core.domain.model.Contact
import com.example.core.domain.repository.CoreRepository
import com.example.core.utils.Resource
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetContactById @Inject constructor(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(
        id: String
    ): Resource<Contact> {
        return repository.getContactById(
            id = id
        )
    }
}