package com.example.profile_domain.repository

import com.example.core.utils.Resource
import com.example.profile_domain.model.Family
import com.example.profile_domain.model.User

interface ProfileRepository {
    suspend fun getUserPersonal(): Resource<User>

    suspend fun addNewFamily(email: String): Resource<String>

    suspend fun getFamily(): Resource<List<Family>>

    suspend fun getPatient(): Resource<List<Family>>

    suspend fun connectWristband(code: String): Resource<String>
}