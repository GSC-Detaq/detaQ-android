package com.example.landing_domain.use_cases

import com.example.core.utils.errors.ValidationError
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ValidateNumber @Inject constructor() {

    operator fun invoke(
        number: String
    ): Result<Unit> {
        if (!number.startsWith("08")) {
            return Result.failure(
                ValidationError("Number should starts with \"08\"")
            )
        }

        if (number.length < 10) {
            return Result.failure(
                ValidationError("Number used to has at least 10 digits")
            )
        }

        return Result.success(Unit)
    }
}