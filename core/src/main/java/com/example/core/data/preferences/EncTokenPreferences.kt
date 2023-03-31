package com.example.core.data.preferences

import android.content.SharedPreferences
import com.example.core.BuildConfig
import com.example.core.domain.preferences.TokenPreferences
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class EncTokenPreferences @Inject constructor(
    @Named("encryptedPreferences") private val preferences: SharedPreferences
): TokenPreferences {
    override fun getToken(): String = preferences.getString(BuildConfig.TOKEN_KEY, "") ?: ""

    override fun setToken(
        token: String
    ) {
        preferences.edit()
            .putString(BuildConfig.TOKEN_KEY, token)
            .apply()
    }

    override fun getFcmToken(): String = preferences.getString(BuildConfig.FCM_TOKEN_KEY, "") ?: ""

    override fun setFcmToken(token: String) {
        preferences.edit()
            .putString(BuildConfig.FCM_TOKEN_KEY, token)
            .apply()
    }
}