package com.example.profile_domain.repository

import com.example.core.utils.Resource
import com.example.profile_domain.model.User

interface ProfileRepository {
    suspend fun getUserPersonal(): Resource<User>

    suspend fun addNewFamily(email: String): Resource<String>
}