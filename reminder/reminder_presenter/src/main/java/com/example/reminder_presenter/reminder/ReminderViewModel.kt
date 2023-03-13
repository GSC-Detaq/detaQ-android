package com.example.reminder_presenter.reminder

import androidx.lifecycle.ViewModel
import com.example.reminder_presenter.reminder.medicine.AddMedicineState
import com.example.reminder_presenter.reminder.medicine.Medicine
import com.example.reminder_presenter.reminder.medicine.MedicineSectionEvent
import com.example.reminder_presenter.reminder.medicine.MedicineSectionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.DayOfWeek

class ReminderViewModel: ViewModel() {
    private val _medicineState = MutableStateFlow(MedicineSectionState())
    val medicineState: StateFlow<MedicineSectionState> = _medicineState.asStateFlow()

    fun onEvent(event: MedicineSectionEvent) {
        when (event) {
            MedicineSectionEvent.AddMedicine -> {
                val newMedicines = medicineState
                    .value
                    .medicines
                    .toMutableList()

                val addMedicineState = medicineState.value.addMedicineState

                newMedicines
                    .add(
                        Medicine(
                            name = addMedicineState.medicineName,
                            dosage = addMedicineState.drugDosage.toIntOrNull() ?: 1,
                            dateStart = addMedicineState.dateStart,
                            dateEnd = addMedicineState.dateEnd,
                            time = addMedicineState.time,
                            day = listOf(
                                DayOfWeek.of(addMedicineState.dateStart.day)
                            )
                        )
                    )

                _medicineState.value = medicineState.value.copy(
                    medicines = newMedicines
                        .distinctBy { it.name },
                    addMedicineState = AddMedicineState()
                )
            }
            is MedicineSectionEvent.OnAddTime -> {
                val newTimes = medicineState
                    .value
                    .addMedicineState
                    .time
                    .toMutableList()

                newTimes
                    .add(event.time)

                _medicineState.value = medicineState.value.copy(
                    addMedicineState = medicineState.value.addMedicineState.copy(
                        time = newTimes
                            .distinctBy { "${it.hour}:${it.minute}" }
                    )
                )
            }
            is MedicineSectionEvent.OnDrugDosageChange -> {
                _medicineState.value = medicineState.value.copy(
                    addMedicineState = medicineState.value.addMedicineState.copy(
                        drugDosage = event.dosage
                    )
                )
            }
            is MedicineSectionEvent.OnMedicineNameChange -> {
                _medicineState.value = medicineState.value.copy(
                    addMedicineState = medicineState.value.addMedicineState.copy(
                        medicineName = event.medicine
                    )
                )
            }
            is MedicineSectionEvent.OnPickDateEnd -> {
                _medicineState.value = medicineState.value.copy(
                    addMedicineState = medicineState.value.addMedicineState.copy(
                        dateEnd = event.date
                    )
                )
            }
            is MedicineSectionEvent.OnPickDateStart -> {
                _medicineState.value = medicineState.value.copy(
                    addMedicineState = medicineState.value.addMedicineState.copy(
                        dateStart = event.date
                    )
                )
            }
            is MedicineSectionEvent.OnRemoveTime -> {
                val newTimes = medicineState
                    .value
                    .addMedicineState
                    .time
                    .toMutableList()

                newTimes
                    .removeIf {
                        it.hour == event.time.hour
                        && it.minute == event.time.hour
                    }

                _medicineState.value = medicineState.value.copy(
                    addMedicineState = medicineState.value.addMedicineState.copy(
                        time = newTimes
                    )
                )
            }
        }
    }
}