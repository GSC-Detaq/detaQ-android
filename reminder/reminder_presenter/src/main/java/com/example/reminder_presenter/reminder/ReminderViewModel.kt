package com.example.reminder_presenter.reminder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.reminder_domain.model.Instruction
import com.example.reminder_domain.use_cases.ReminderUseCases
import com.example.reminder_presenter.reminder.doctor.AddDoctorState
import com.example.reminder_presenter.reminder.doctor.DoctorSectionEvent
import com.example.reminder_presenter.reminder.doctor.DoctorSectionState
import com.example.reminder_presenter.reminder.medicine.AddMedicineState
import com.example.reminder_presenter.reminder.medicine.MedicineSectionEvent
import com.example.reminder_presenter.reminder.medicine.MedicineSectionState
import com.example.reminder_presenter.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ReminderViewModel @Inject constructor(
    private val reminderUseCases: ReminderUseCases
): ViewModel() {
    init {
        initMedicine()
        initDoctor()
    }

    private val _medicineState = MutableStateFlow(MedicineSectionState())
    val medicineState: StateFlow<MedicineSectionState> = _medicineState.asStateFlow()

    private val _medicineUiEvent = Channel<UiEvent>()
    val medicineUiEvent = _medicineUiEvent.receiveAsFlow()

    fun onEvent(event: MedicineSectionEvent) {
        when (event) {
            MedicineSectionEvent.AddMedicine -> {
                viewModelScope.launch {
                    val addMedicineState = medicineState.value.addMedicineState
                    val result = reminderUseCases.addMedicineReminder(
                        medicineName = addMedicineState.medicineName,
                        medicineDosage = addMedicineState.drugDosage,
                        dateStart = addMedicineState.dateStart,
                        dateEnd = addMedicineState.dateEnd,
                        time = addMedicineState.time,
                        instruction = addMedicineState.instructions ?: Instruction.AfterEat
                    )

                    when(result) {
                        is Resource.Error -> {
                            Timber.d(result.message)
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _medicineUiEvent.send(
                                UiEvent.Success(result.data ?: return@launch)
                            )

                            initMedicine()
                        }
                    }
                }
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
            MedicineSectionEvent.ResetAddState -> {
                _medicineState.value = medicineState.value.copy(
                    addMedicineState = AddMedicineState()
                )
            }
            is MedicineSectionEvent.OnPickInstruction -> {
                _medicineState.value = medicineState.value.copy(
                    addMedicineState = medicineState.value.addMedicineState.copy(
                        instructions = event.instruction
                    )
                )
            }
            is MedicineSectionEvent.ToggleInstructionDropDown -> {
                _medicineState.value = medicineState.value.copy(
                    addMedicineState = medicineState.value.addMedicineState.copy(
                        isInstructionOpen = event.isOpen
                    )
                )
            }
        }
    }

    private fun initMedicine() {
        viewModelScope.launch {
            when(val result = reminderUseCases.getMedicineReminders()) {
                is Resource.Error -> {
                    Timber.d(result.message)
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _medicineState.value = medicineState.value.copy(
                        medicines = result.data ?: emptyList()
                    )
                }
            }
        }
    }

    private val _doctorState = MutableStateFlow(DoctorSectionState())
    val doctorState: StateFlow<DoctorSectionState> = _doctorState.asStateFlow()

    private val _doctorUiEvent = Channel<UiEvent>()
    val doctorUiEvent = _doctorUiEvent.receiveAsFlow()

    fun onEvent(event: DoctorSectionEvent) {
        when (event) {
            DoctorSectionEvent.AddDoctor -> {
                viewModelScope.launch {
                    val addDoctorState = doctorState.value.addDoctorState
                    val result = reminderUseCases.addDoctorReminder(
                        activity = addDoctorState.activity,
                        doctorName = addDoctorState.doctorName,
                        date = addDoctorState.date,
                        time = addDoctorState.time
                    )

                    when(result) {
                        is Resource.Error -> {
                            Timber.d(result.message)
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _doctorUiEvent.send(
                                UiEvent.Success(result.data ?: return@launch)
                            )

                            initDoctor()
                        }
                    }
                }
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
            DoctorSectionEvent.ResetAddState -> {
                _doctorState.value = doctorState.value.copy(
                    addDoctorState = AddDoctorState()
                )
            }
        }
    }

    private fun initDoctor() {
        viewModelScope.launch {
            when(val result = reminderUseCases.getDoctorReminders()) {
                is Resource.Error -> {
                    Timber.d(result.message)
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _doctorState.value = doctorState.value.copy(
                        doctors = result.data ?: emptyList()
                    )
                }
            }
        }
    }
}