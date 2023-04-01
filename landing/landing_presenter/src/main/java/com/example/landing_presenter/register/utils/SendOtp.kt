package com.example.landing_presenter.register.utils

import android.app.Activity
import com.example.core.utils.Resource
import com.example.landing_presenter.register.model.OtpResult
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

fun sendOtp(
    number: String,
    activity: Activity,
    enabled: Boolean,
    onResult: (Resource<OtpResult>) -> Unit
) {
    if (!enabled) {
        return
    }

    onResult(Resource.Loading())

    val auth = Firebase.auth
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            auth
                .signInWithCredential(p0)
                .addOnCompleteListener { result ->
                    when {
                        result.isComplete -> {
                            onResult(
                                Resource.Success(
                                    OtpResult(
                                        verificationId = "",
                                        isVerificationCompleted = true
                                    )
                                )
                            )
                        }
                        result.isCanceled -> {
                            onResult(
                                Resource.Error(
                                    result.exception?.message ?: "Send Otp Failed"
                                )
                            )
                        }
                    }
                }
                .addOnCanceledListener {
                    onResult(
                        Resource.Error(
                            "Send Otp Failed"
                        )
                    )
                }
                .addOnFailureListener { exception ->
                    onResult(
                        Resource.Error(
                            exception.message ?: "Send Otp Failed"
                        )
                    )
                }
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            onResult(
                Resource.Error(p0.message ?: "Send Otp Failed")
            )
        }

        override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(p0, p1)

            onResult(
                Resource.Success(
                    OtpResult(
                        verificationId = p0,
                        isVerificationCompleted = false,
                        forceResendingToken = p1
                    )
                )
            )
        }
    }

    val options = PhoneAuthOptions.newBuilder(auth)
        .setPhoneNumber(number)
        .setActivity(activity)
        .setTimeout(30L, TimeUnit.SECONDS)
        .setCallbacks(callbacks)
        .build()

    PhoneAuthProvider.verifyPhoneNumber(options)
}