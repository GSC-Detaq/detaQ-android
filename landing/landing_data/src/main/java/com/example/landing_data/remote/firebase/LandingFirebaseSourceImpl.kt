package com.example.landing_data.remote.firebase

import com.example.core.utils.Resource
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class LandingFirebaseSourceImpl: LandingFirebaseSource {
    private val auth = Firebase.auth

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
                .addOnSuccessListener {
                    trySend(
                        Resource.Success(Unit)
                    )
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