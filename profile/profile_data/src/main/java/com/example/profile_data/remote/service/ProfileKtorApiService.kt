package com.example.profile_data.remote.service

import com.example.profile_data.BuildConfig
import com.example.profile_data.remote.dto.response.AddNewFamilyResponse
import com.example.profile_data.remote.dto.response.ConnectWristbandResponse
import com.example.profile_data.remote.dto.response.FamilyResponse
import com.example.profile_data.remote.dto.response.UserResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ProfileKtorApiService(
    private val client: HttpClient
): ProfileApiService {
    override suspend fun getUserPersonal(): UserResponse {
        val result = client.get {
            url(GET_USER_PERSONAL)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun addNewFamily(
        email: String
    ): AddNewFamilyResponse {
        val result = client.get {
            url(ADD_NEW_FAMILY + "email=$email")
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun getFamily(): FamilyResponse {
        val result = client.get {
            url(GET_FAMILY)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun getPatient(): FamilyResponse {
        val result = client.get {
            url(GET_PATIENT)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun connectWristband(
        code: String
    ): ConnectWristbandResponse {
        val result = client.get {
            url(CONNECT_WRISTBAND + "code=$code")
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    companion object {
        private const val BASE_URL = "http://${BuildConfig.BASE_URL}"
        private const val GET_USER_PERSONAL = "$BASE_URL/user/myuser"
        private const val ADD_NEW_FAMILY = "$BASE_URL/family/add?"
        private const val GET_FAMILY = "$BASE_URL/family/getall/family"
        private const val GET_PATIENT = "$BASE_URL/family/getall/patient"
        private const val CONNECT_WRISTBAND = "$BASE_URL/iot/androidpair?"
    }
}