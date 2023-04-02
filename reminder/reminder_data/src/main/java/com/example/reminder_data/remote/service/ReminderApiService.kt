package com.example.reminder_data.remote.service

import com.example.reminder_data.remote.dto.request.AddDoctorReminderRequest
import com.example.reminder_data.remote.dto.request.AddMedicineReminderRequest
import com.example.reminder_data.remote.dto.request.AddReminderNotificationRequest
import com.example.reminder_data.remote.dto.response.AddReminderNotificationResponse
import com.example.reminder_data.remote.dto.response.AddReminderResponse
import com.example.reminder_data.remote.dto.response.DoctorReminderResponse
import com.example.reminder_data.remote.dto.response.MedicineReminderResponse

interface ReminderApiService {

    suspend fun addMedicineReminder(request: AddMedicineReminderRequest): AddReminderResponse

    suspend fun getMedicineReminders(): MedicineReminderResponse

    suspend fun addMedicineReminderNotification(
        request: AddReminderNotificationRequest
    ): AddReminderNotificationResponse

    suspend fun addDoctorReminder(request: AddDoctorReminderRequest): AddReminderResponse

    suspend fun getDoctorReminders(): DoctorReminderResponse

    suspend fun addDoctorReminderNotification(
        request: AddReminderNotificationRequest
    ): AddReminderNotificationResponse
}