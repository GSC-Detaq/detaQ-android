package com.example.reminder_domain.use_cases

data class ReminderUseCases(
    val addMedicineReminder: AddMedicineReminder,
    val getMedicineReminders: GetMedicineReminders,
    val addDoctorReminder: AddDoctorReminder,
    val getDoctorReminders: GetDoctorReminders
)
