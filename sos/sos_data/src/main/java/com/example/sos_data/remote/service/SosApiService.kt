package com.example.sos_data.remote.service

import com.example.sos_data.remote.dto.request.SendSosRequest
import com.example.sos_data.remote.dto.response.SendSosResponse

interface SosApiService {

    suspend fun sendSos(request: SendSosRequest): SendSosResponse
}