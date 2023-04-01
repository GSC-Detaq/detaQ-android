package com.example.landing_data.remote.firebase

import com.example.core.utils.Resource
import com.example.landing_domain.model.OtpResult
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit

class LandingFirebaseSourceImpl: LandingFirebaseSource {
    private val auth = Firebase.auth

    override fun sendOtp(number: String): Flow<Resource<OtpResult>> {
        return callbackFlow {
            trySend(Resource.Loading())

            val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    auth
                        .signInWithCredential(p0)
                        .addOnCompleteListener { result ->
                            when {
                                result.isComplete -> {
                                    trySend(
                                        Resource.Success(
                                            OtpResult(
                                                verificationId = "",
                                                isVerificationCompleted = true
                                            )
                                        )
                                    )
                                }
                                result.isCanceled -> {
                                    trySend(
                                        Resource.Error(
                                            result.exception?.message ?: "Send Otp Failed"
                                        )
                                    )

                                    cancel()
                                }
                            }
                        }
                        .addOnCanceledListener {
                            trySend(
                                Resource.Error(
                                    "Send Otp Failed"
                                )
                            )

                            cancel()
                        }
                        .addOnFailureListener { exception ->
                            trySend(
                                Resource.Error(
                                    exception.message ?: "Send Otp Failed"
                                )
                            )

                            cancel()
                        }
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    trySend(
                        Resource.Error(p0.message ?: "Send Otp Failed")
                    )

                    cancel()
                }

                override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(p0, p1)

                    trySend(
                        Resource.Success(
                            OtpResult(
                                verificationId = p0,
                                isVerificationCompleted = false
                            )
                        )
                    )
                }
            }

            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(number)
                .setTimeout(30L, TimeUnit.SECONDS)
                .setCallbacks(callbacks)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(options)

            awaitClose {
                close()
            }
        }
    }

    override fun verifyOtp(
        verificationId: String,
        otp: String
    ): Flow<Resource<Unit>> {
        return callbackFlow {
            trySend(Resource.Loading())

            val credential = PhoneAuthProvider
                .getCredential(
                    verificationId,
                    otp
                )

            auth
                .signInWithCredential(credential)
                .addOnCompleteListener { result ->
                    when {
                        result.isComplete -> {
                            trySend(
                                Resource.Success(Unit)
                            )
                        }
                        result.isCanceled -> {
                            trySend(
                                Resource.Error(
                                    result.exception?.message ?: "Send Otp Failed"
                                )
                            )

                            cancel()
                        }
                    }
                }
                .addOnCanceledListener {
                    trySend(
                        Resource.Error(
                            "Send Otp Failed"
                        )
                    )

                    cancel()
                }
                .addOnFailureListener { exception ->
                    trySend(
                        Resource.Error(
                            exception.message ?: "Send Otp Failed"
                        )
                    )

                    cancel()
                }

            awaitClose {
                cancel()
            }
        }
    }
}