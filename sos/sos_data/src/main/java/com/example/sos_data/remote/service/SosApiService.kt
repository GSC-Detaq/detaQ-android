package com.example.sos_data.remote.service

import com.example.sos_data.remote.dto.request.AddSosNotificationRequest
import com.example.sos_data.remote.dto.request.SendSosRequest
import com.example.sos_data.remote.dto.response.AddSosNotificationResponse
import com.example.sos_data.remote.dto.response.SendSosResponse

interface SosApiService {
    suspend fun sendSos(request: SendSosRequest): SendSosResponse

    suspend fun addSosNotification(request: AddSosNotificationRequest): AddSosNotificationResponse
}