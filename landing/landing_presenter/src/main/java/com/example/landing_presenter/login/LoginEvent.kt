package com.example.landing_presenter.login

sealed class LoginEvent {
    data class OnEmailChange(val email: String): LoginEvent()
    data class OnPasswordChange(val password: String): LoginEvent()
    object ToggleShowPassword: LoginEvent()
    object Login: LoginEvent()
}