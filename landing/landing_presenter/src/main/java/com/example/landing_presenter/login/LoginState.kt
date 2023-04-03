package com.example.landing_presenter.login

import com.example.core.utils.errors.ValidationError

data class LoginState(
    val email: String = "",
    val emailError: ValidationError? = null,
    val password: String = "",
    val passwordError: ValidationError? = null,
    val isPasswordVisible: Boolean = false,
    val loginButtonEnabled: Boolean = false,
    val loginError: String? = ""
)