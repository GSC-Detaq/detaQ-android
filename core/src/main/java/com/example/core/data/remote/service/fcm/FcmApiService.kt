package com.example.core.data.remote.service.fcm

import com.example.core.data.remote.dto.request.UpdateFcmTokenRequest
import com.example.core.data.remote.dto.response.UpdateFcmTokenResponse

interface FcmApiService {

    suspend fun updateToken(request: UpdateFcmTokenRequest): UpdateFcmTokenResponse
}