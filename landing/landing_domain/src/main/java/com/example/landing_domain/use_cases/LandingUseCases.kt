package com.example.landing_domain.use_cases

data class LandingUseCases(
    val login: Login,
    val register: Register,
    val verifyOtp: VerifyOtp,
    val validateNumber: ValidateNumber,
    val validatePassword: ValidatePassword
)
