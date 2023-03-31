package com.example.reminder_data.remote.service

import com.example.reminder_data.BuildConfig
import com.example.reminder_data.remote.dto.request.AddDoctorReminderRequest
import com.example.reminder_data.remote.dto.request.AddMedicineReminderRequest
import com.example.reminder_data.remote.dto.response.AddReminderResponse
import com.example.reminder_data.remote.dto.response.DoctorReminderResponse
import com.example.reminder_data.remote.dto.response.MedicineReminderResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ReminderKtorApiService(
    private val client: HttpClient
): ReminderApiService {
    override suspend fun addMedicineReminder(request: AddMedicineReminderRequest): AddReminderResponse {
        val result = client.post {
            url(ADD_MEDICINE_REMINDER_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun getMedicineReminders(): MedicineReminderResponse {
        val result = client.get {
            url(GET_MEDICINE_REMINDER_URL)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    override suspend fun addDoctorReminder(request: AddDoctorReminderRequest): AddReminderResponse {
        val result = client.post {
            url(ADD_DOCTOR_REMINDER_URL)
            contentType(ContentType.Application.Json)

            setBody(request)
        }

        return result.body()
    }

    override suspend fun getDoctorReminders(): DoctorReminderResponse {
        val result = client.get {
            url(GET_DOCTOR_REMINDER_URL)
            contentType(ContentType.Application.Json)
        }

        return result.body()
    }

    companion object {
        private const val BASE_URL = "http://${BuildConfig.BASE_URL}"
        private const val ADD_MEDICINE_REMINDER_URL = "$BASE_URL/med_reminder/add"
        private const val GET_MEDICINE_REMINDER_URL = "$BASE_URL/med_reminder/all"
        private const val ADD_DOCTOR_REMINDER_URL = "$BASE_URL/doc_reminder/add"
        private const val GET_DOCTOR_REMINDER_URL = "$BASE_URL/doc_reminder/all"
    }

}