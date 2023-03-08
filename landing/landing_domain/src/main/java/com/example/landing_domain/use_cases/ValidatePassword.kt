package com.example.landing_domain.use_cases

import com.example.core.utils.errors.ValidationError
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ValidatePassword @Inject constructor() {

    operator fun invoke(
        password: String
    ): Result<Unit> {
        if (password.length < 8) {
            return Result.failure(
                ValidationError("Password used to has at least 8 characters")
            )
        }

        return Result.success(Unit)
    }
}