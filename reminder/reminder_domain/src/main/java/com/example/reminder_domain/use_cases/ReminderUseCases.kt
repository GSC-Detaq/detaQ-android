package com.example.reminder_domain.use_cases

data class ReminderUseCases(
    val addMedicineReminder: AddMedicineReminder,
    val getMedicineReminders: GetMedicineReminders,
    val addMedicineReminderNotification: AddMedicineReminderNotification,
    val endMedicineReminder: EndMedicineReminder,
    val addDoctorReminder: AddDoctorReminder,
    val getDoctorReminders: GetDoctorReminders,
    val addDoctorReminderNotification: AddDoctorReminderNotification,
    val endDoctorReminder: EndDoctorReminder
)
