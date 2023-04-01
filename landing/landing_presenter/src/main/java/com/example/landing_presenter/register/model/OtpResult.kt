package com.example.landing_presenter.register.model

import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken

data class OtpResult(
    val verificationId: String,
    val isVerificationCompleted: Boolean,
    val forceResendingToken: ForceResendingToken? = null
)
