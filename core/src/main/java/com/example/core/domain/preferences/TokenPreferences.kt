package com.example.core.domain.preferences

interface TokenPreferences {
    fun getToken(): String
    fun setToken(token: String)

    fun getFcmToken(): String
    fun setFcmToken(token: String)
}