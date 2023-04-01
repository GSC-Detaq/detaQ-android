package com.example.landing_presenter.register

import com.example.core.utils.Resource
import com.example.landing_presenter.register.model.OtpResult

sealed class RegisterEvent {
    data class OnNameChange(val name: String): RegisterEvent()
    data class OnEmailChange(val email: String): RegisterEvent()
    data class OnPasswordChange(val password: String): RegisterEvent()
    object ToggleShowPassword: RegisterEvent()
    data class OnNumberChange(val number: String): RegisterEvent()
    data class OnSendOtpResult(val result: Resource<OtpResult>): RegisterEvent()
    data class OnOtpChange(val otp: String): RegisterEvent()
    object OnVerifyOtp: RegisterEvent()
    data class OnPickRole(val role: Role): RegisterEvent()
    data class ToggleRoleDropDown(val isOpen: Boolean): RegisterEvent()
    data class UpdateSection(val section: RegisterSection): RegisterEvent()
    object Register: RegisterEvent()
}