package com.example.reminder_domain.repository

import com.example.core.utils.Resource
import com.example.reminder_domain.model.DoctorReminder
import com.example.reminder_domain.model.MedicineReminder

interface ReminderRepository {

    suspend fun addMedicineReminder(
        medicineName: String,
        medicineDosage: String,
        dateStart: String,
        dateEnd: String,
        time: String,
        instruction: String
    ): Resource<String>

    suspend fun getMedicineReminders(): Resource<List<MedicineReminder>>

    suspend fun addDoctorReminder(
        activity: String,
        doctorName: String,
        date: String,
        time: String
    ): Resource<String>

    suspend fun getDoctorReminders(): Resource<List<DoctorReminder>>
}