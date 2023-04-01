package com.example.home_data.remote.service

import io.ktor.client.*

class HomeKtorApiService(
    private val client: HttpClient
): HomeApiService {
}