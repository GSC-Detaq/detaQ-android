package com.example.reminder_data.repository

import com.example.core.data.utils.ApiResponse
import com.example.core.utils.Resource
import com.example.reminder_data.mapper.toDoctorReminder
import com.example.reminder_data.mapper.toMedicineReminder
import com.example.reminder_data.remote.dto.request.AddDoctorReminderRequest
import com.example.reminder_data.remote.dto.request.AddMedicineReminderRequest
import com.example.reminder_data.remote.dto.request.AddReminderNotificationRequest
import com.example.reminder_data.remote.source.ReminderRemoteDataSource
import com.example.reminder_domain.model.DoctorReminder
import com.example.reminder_domain.model.MedicineReminder
import com.example.reminder_domain.repository.ReminderRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReminderRepositoryImpl @Inject constructor(
    private val remoteDataSource: ReminderRemoteDataSource,
): ReminderRepository {
    override suspend fun addMedicineReminder(
        medicineName: String,
        medicineDosage: String,
        dateStart: String,
        dateEnd: String,
        time: String,
        instruction: String,
    ): Resource<String> {
        val request = AddMedicineReminderRequest(
            medicine_name = medicineName,
            medicine_dosage = medicineDosage,
            date_start = dateStart,
            date_end = dateEnd,
            instruction = instruction,
            time = time
        )

        return when(val result = remoteDataSource.addMedicineReminder(request)) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(result.data.created_id_related)
            }
        }
    }

    override suspend fun getMedicineReminders(): Resource<List<MedicineReminder>> {
        return when(val result = remoteDataSource.getMedicineReminders()) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(
                    result.data.map {
                        it.toMedicineReminder()
                    }
                )
            }
        }
    }

    override suspend fun addMedicineReminderNotification(
        title: String,
        body: String,
    ): Resource<String> {
        val request = AddReminderNotificationRequest(
            title = title,
            body = body,
            timestamp = System.currentTimeMillis().toString()
        )

        return when(
            val result = remoteDataSource
                .addMedicineReminderNotification(request)
        ) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(result.data)
            }
        }
    }

    override suspend fun endDoctorReminder(reminderId: String): Resource<String> {
        return when(
            val result = remoteDataSource
                .endMedicineReminder(
                    reminderId = reminderId
                )
        ) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(result.data)
            }
        }
    }

    override suspend fun addDoctorReminder(
        activity: String,
        doctorName: String,
        date: String,
        time: String,
    ): Resource<String> {
        val request = AddDoctorReminderRequest(
            activity = activity,
            doctor_name = doctorName,
            date = date,
            time = time
        )

        return when(val result = remoteDataSource.addDoctorReminder(request)) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(result.data.created_id_related)
            }
        }
    }

    override suspend fun getDoctorReminders(): Resource<List<DoctorReminder>> {
        return when(val result = remoteDataSource.getDoctorReminders()) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(
                    result.data.map {
                        it.toDoctorReminder()
                    }
                )
            }
        }
    }

    override suspend fun addDoctorReminderNotification(
        title: String,
        body: String,
    ): Resource<String> {
        val request = AddReminderNotificationRequest(
            title = title,
            body = body,
            timestamp = System.currentTimeMillis().toString()
        )

        return when(
            val result = remoteDataSource
                .addDoctorReminderNotification(request)
        ) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(result.data)
            }
        }
    }

    override suspend fun endMedicineReminder(reminderId: String): Resource<String> {
        return when(
            val result = remoteDataSource
                .endDoctorReminder(
                    reminderId = reminderId
                )
        ) {
            ApiResponse.Empty -> {
                Resource.Error("Unexpected Error")
            }
            is ApiResponse.Error -> {
                Resource.Error(result.errorMessage)
            }
            is ApiResponse.Success -> {
                Resource.Success(result.data)
            }
        }
    }
}