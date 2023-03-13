package com.example.reminder_presenter.reminder.medicine

import java.util.Date

sealed class MedicineSectionEvent {
    object AddMedicine: MedicineSectionEvent()
    data class OnMedicineNameChange(val medicine: String): MedicineSectionEvent()
    data class OnDrugDosageChange(val dosage: String): MedicineSectionEvent()
    data class OnPickDateStart(val date: Date): MedicineSectionEvent()
    data class OnPickDateEnd(val date: Date): MedicineSectionEvent()
    data class OnAddTime(val time: Time): MedicineSectionEvent()
    data class OnRemoveTime(val time: Time): MedicineSectionEvent()
}