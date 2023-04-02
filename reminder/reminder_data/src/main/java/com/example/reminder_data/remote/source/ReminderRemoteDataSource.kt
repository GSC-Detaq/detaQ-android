package com.example.reminder_data.remote.source

import com.example.core.data.remote.utils.tryCatch
import com.example.core.data.utils.ApiResponse
import com.example.core.domain.dispatchers.DispatchersProvider
import com.example.reminder_data.remote.dto.request.AddDoctorReminderRequest
import com.example.reminder_data.remote.dto.request.AddMedicineReminderRequest
import com.example.reminder_data.remote.dto.request.AddReminderNotificationRequest
import com.example.reminder_data.remote.dto.response.AddReminderResponse
import com.example.reminder_data.remote.dto.response.DoctorReminderResponse
import com.example.reminder_data.remote.dto.response.MedicineReminderResponse
import com.example.reminder_data.remote.service.ReminderApiService
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderRemoteDataSource @Inject constructor(
    private val apiService: ReminderApiService,
    private val dispatchers: DispatchersProvider
) {
    suspend fun addMedicineReminder(
        request: AddMedicineReminderRequest
    ): ApiResponse<AddReminderResponse.Data> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.addMedicineReminder(request)

                if (result.meta.success) {
                    ApiResponse.Success(result.data)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun getMedicineReminders(): ApiResponse<List<MedicineReminderResponse.Data>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getMedicineReminders()

                if (result.meta.success) {
                    ApiResponse.Success(result.data)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun addMedicineReminderNotification(
        request: AddReminderNotificationRequest
    ): ApiResponse<String> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.addMedicineReminderNotification(request)

                if (result.meta.success) {
                    ApiResponse.Success(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun endMedicineReminder(
        reminderId: String
    ): ApiResponse<String> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.endMedicineReminder(
                    reminderId = reminderId
                )

                if (result.meta.success) {
                    ApiResponse.Success(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun addDoctorReminder(
        request: AddDoctorReminderRequest
    ): ApiResponse<AddReminderResponse.Data> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.addDoctorReminder(request)

                if (result.meta.success) {
                    ApiResponse.Success(result.data)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun getDoctorReminders(): ApiResponse<List<DoctorReminderResponse.Data>> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.getDoctorReminders()

                if (result.meta.success) {
                    ApiResponse.Success(result.data)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun addDoctorReminderNotification(
        request: AddReminderNotificationRequest
    ): ApiResponse<String> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.addDoctorReminderNotification(request)

                if (result.meta.success) {
                    ApiResponse.Success(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }

    suspend fun endDoctorReminder(
        reminderId: String
    ): ApiResponse<String> {
        return withContext(dispatchers.io) {
            tryCatch {
                val result = apiService.endDoctorReminder(
                    reminderId = reminderId
                )

                if (result.meta.success) {
                    ApiResponse.Success(result.meta.message)
                }
                else {
                    ApiResponse.Error(result.meta.message)
                }
            }
        }
    }
}