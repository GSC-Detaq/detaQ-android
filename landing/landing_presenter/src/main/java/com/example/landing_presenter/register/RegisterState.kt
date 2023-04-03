package com.example.landing_presenter.register

import com.example.core.utils.errors.ValidationError
import com.example.landing_presenter.R

data class RegisterState(
    val currentSection: RegisterSection = RegisterSection.CreateAccount,
    val name: String = "",
    val nameError: ValidationError? = null,
    val email: String = "",
    val emailError: ValidationError? = null,
    val password: String = "",
    val passwordError: ValidationError? = null,
    val isPasswordVisible: Boolean = false,
    val registerEnabled: Boolean = false,
    val number: String = "",
    val numberError: ValidationError? = null,
    val sendOtpEnabled: Boolean = false,
    val otp: String = "",
    val otpError: ValidationError? = null,
    val verifyOtpEnabled: Boolean = false,
    val otpSendCount: Int = 0,
    val otpCountDown: Int = 0,
    val role: Role? = null,
    val roleError: ValidationError? = null,
    val roleDropDownOpen: Boolean = false,
    val confirmationEnabled: Boolean = false,
    val verificationId: String? = null,
    val sendOtpError: String? = null,
    val sendOtpLoading: Boolean = false,
    val verifyOtpError: String? = null,
    val verifyOtpLoading: Boolean = false,
    val registerLoading: Boolean = false,
    val registerError: String? = null
)

enum class Role {
    Patient,
    Family,
    Others;

    fun getRoleId(): Int {
        return when(this) {
            Patient -> 1
            Family -> 2
            Others -> 3
        }
    }
}

enum class UserRole {
    Patient,
    Family;

    fun getIconRes(): Int {
        return when(this) {
            Patient -> R.drawable.patient_icon
            Family -> R.drawable.family_icon
        }
    }

    fun getRoleAsTextRes(): Int {
        return when(this) {
            Patient -> R.string.patient
            Family -> R.string.family
        }
    }

    fun getRoleId(): Int {
        return when(this) {
            Patient -> 1
            Family -> 2
        }
    }
}

enum class RegisterSection {
    CreateAccount,
    FillNumber,
    NumberVerification,
    SelectRole
}