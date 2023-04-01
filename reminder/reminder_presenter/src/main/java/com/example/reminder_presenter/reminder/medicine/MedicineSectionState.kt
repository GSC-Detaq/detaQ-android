package com.example.reminder_presenter.reminder.medicine

import com.example.reminder_domain.model.Instruction
import com.example.reminder_domain.model.MedicineReminder
import com.example.core.domain.model.Time
import java.time.LocalDate

data class MedicineSectionState(
    val medicines: List<MedicineReminder> = emptyList(),
    val addMedicineState: AddMedicineState = AddMedicineState()
)

data class AddMedicineState(
    val medicineName: String = "",
    val drugDosage: String = "",
    val dateStart: LocalDate = LocalDate.now(),
    val dateEnd: LocalDate = LocalDate.now(),
    val time: List<Time> = emptyList(),
    val instructions: Instruction? =  null,
    val isInstructionOpen: Boolean = false
)