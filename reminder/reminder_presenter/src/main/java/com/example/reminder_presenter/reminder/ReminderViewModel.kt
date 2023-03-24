package com.example.reminder_presenter.reminder

import androidx.lifecycle.ViewModel
import com.example.reminder_presenter.reminder.doctor.AddDoctorState
import com.example.reminder_presenter.reminder.doctor.Doctor
import com.example.reminder_presenter.reminder.doctor.DoctorSectionEvent
import com.example.reminder_presenter.reminder.doctor.DoctorSectionState
import com.example.reminder_presenter.reminder.medicine.AddMedicineState
import com.example.reminder_presenter.reminder.medicine.Medicine
import com.example.reminder_presenter.reminder.medicine.MedicineSectionEvent
import com.example.reminder_presenter.reminder.medicine.MedicineSectionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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
                                addMedicineState.dateStart.dayOfWeek
                            )
                        )
                    )

                _medicineState.value = medicineState.value.copy(
                    medicines = newMedicines
                        .asReversed()
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
                            .asReversed()
                            .distinctBy { it.hour to it.minute }
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

    private val _doctorState = MutableStateFlow(DoctorSectionState())
    val doctorState: StateFlow<DoctorSectionState> = _doctorState.asStateFlow()

    fun onEvent(event: DoctorSectionEvent) {
        when (event) {
            DoctorSectionEvent.AddDoctor -> {
                val newDoctors = doctorState
                    .value
                    .doctors
                    .toMutableList()

                val addDoctorState = doctorState
                    .value
                    .addDoctorState

                newDoctors.add(
                    Doctor(
                        name = addDoctorState.doctorName,
                        activity = addDoctorState.activity,
                        date = addDoctorState.date
                    )
                )

                _doctorState.value = doctorState.value.copy(
                    doctors = newDoctors,
                    addDoctorState = AddDoctorState()
                )
            }
            is DoctorSectionEvent.OnActivityChange -> {
                _doctorState.value = doctorState.value.copy(
                    addDoctorState = doctorState.value.addDoctorState.copy(
                        activity = event.activity
                    )
                )
            }
            is DoctorSectionEvent.OnDoctorNameChange -> {
                _doctorState.value = doctorState.value.copy(
                    addDoctorState = doctorState.value.addDoctorState.copy(
                        doctorName = event.name
                    )
                )
            }
            is DoctorSectionEvent.OnPickDate -> {
                _doctorState.value = doctorState.value.copy(
                    addDoctorState = doctorState.value.addDoctorState.copy(
                        date = event.date
                    )
                )
            }
            is DoctorSectionEvent.OnPickTime -> {
                _doctorState.value = doctorState.value.copy(
                    addDoctorState = doctorState.value.addDoctorState.copy(
                        time = event.time
                    )
                )
            }
        }
    }
}