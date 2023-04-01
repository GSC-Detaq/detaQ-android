package com.example.reminder_presenter.reminder.medicine

import com.example.reminder_domain.model.Instruction
import com.example.core.domain.model.Time
import java.time.LocalDate

sealed class MedicineSectionEvent {
    object AddMedicine: MedicineSectionEvent()
    data class OnMedicineNameChange(val medicine: String): MedicineSectionEvent()
    data class OnDrugDosageChange(val dosage: String): MedicineSectionEvent()
    data class OnPickDateStart(val date: LocalDate): MedicineSectionEvent()
    data class OnPickDateEnd(val date: LocalDate): MedicineSectionEvent()
    data class OnAddTime(val time: Time): MedicineSectionEvent()
    data class OnRemoveTime(val time: Time): MedicineSectionEvent()
    data class OnPickInstruction(val instruction: Instruction): MedicineSectionEvent()
    data class ToggleInstructionDropDown(val isOpen: Boolean): MedicineSectionEvent()
    object ResetAddState: MedicineSectionEvent()
}