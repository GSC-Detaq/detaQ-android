package com.example.landing_data.remote.service

import io.ktor.client.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LandingKtorApiService @Inject constructor(
    private val httpClient: HttpClient
) {
}