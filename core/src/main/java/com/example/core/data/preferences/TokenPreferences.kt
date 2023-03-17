package com.example.core.data.preferences

import android.content.SharedPreferences
import com.example.core.BuildConfig
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class TokenPreferences @Inject constructor(
    @Named("encryptedPreferences") private val preferences: SharedPreferences
) {
    fun getToken(): String = preferences.getString(BuildConfig.TOKEN_KEY, "") ?: ""

    fun setToken(
        token: String
    ) {
        preferences.edit()
            .putString(BuildConfig.TOKEN_KEY, token)
            .apply()
    }
}