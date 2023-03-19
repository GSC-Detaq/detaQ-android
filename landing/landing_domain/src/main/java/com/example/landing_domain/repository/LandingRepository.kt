package com.example.landing_domain.repository

import com.example.core.utils.Resource

interface LandingRepository {
    suspend fun login(
        email: String,
        password: String
    ): Resource<Unit>

    suspend fun register(
        email: String,
        password: String,
        name: String,
        roleId: Int
    ): Resource<Unit>
}