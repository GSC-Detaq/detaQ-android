package com.example.core.domain.use_cases

import com.example.core.utils.errors.ValidationError
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
import java.util.regex.Pattern

@ViewModelScoped
class ValidateEmail @Inject constructor() {

    operator fun invoke(
        email: String
    ): Result<Unit> {
       if (!isValidEmail(email)) {
           return Result.failure(
               ValidationError("Email format is wrong!")
           )
       }

        return Result.success(Unit)
    }

    private fun isValidEmail(email: String): Boolean {
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)

        return matcher.matches()
    }
}