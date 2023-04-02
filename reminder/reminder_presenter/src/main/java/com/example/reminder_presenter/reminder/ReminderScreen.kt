package com.example.reminder_presenter.reminder

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.ui.theme.Neutral10
import com.example.reminder_domain.utils.rangeOfDates
import com.example.reminder_presenter.R
import com.example.reminder_presenter.reminder.components.ReminderHeader
import com.example.reminder_presenter.reminder.doctor.DoctorSection
import com.example.reminder_presenter.reminder.doctor.DoctorSectionEvent
import com.example.reminder_presenter.reminder.doctor.components.AddDoctorSectionSheet
import com.example.reminder_presenter.reminder.medicine.MedicineSection
import com.example.reminder_presenter.reminder.medicine.MedicineSectionEvent
import com.example.reminder_presenter.reminder.medicine.components.AddMedicineSheet
import com.example.reminder_presenter.utils.ReminderReceiver
import com.example.reminder_presenter.utils.UiEvent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun ReminderScreen(
    viewModel: ReminderViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val medicineState by viewModel.medicineState.collectAsState()
    val doctorState by viewModel.doctorState.collectAsState()
    val pagerState = rememberPagerState()

    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )
    val coroutineScope = rememberCoroutineScope()

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.medicineUiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> {
                    val addMedicineState = medicineState.addMedicineState
                    val listOfDates = rangeOfDates(
                        start = addMedicineState.dateStart,
                        end = addMedicineState.dateEnd
                    )
                    ReminderReceiver.setReminders(
                        context = context,
                        dates = listOfDates,
                        times = addMedicineState.time,
                        title = "Eat Medicine Reminder!",
                        description = "Eat your ${addMedicineState.medicineName}! dosage: ${addMedicineState.drugDosage}",
                        type = "1",
                        id = event.id
                    )

                    scaffoldState.bottomSheetState.collapse()
                    viewModel.onEvent(MedicineSectionEvent.ResetAddState)
                }
                else -> Unit
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.doctorUiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> {
                    val addDoctorState = doctorState.addDoctorState

                    ReminderReceiver.setReminders(
                        context = context,
                        dates = listOf(addDoctorState.date),
                        times = listOf(addDoctorState.time),
                        title = "Doctor Appointment Reminder!",
                        description = "Meet your doctor: ${addDoctorState.doctorName} at: ${addDoctorState.time.hour}:${addDoctorState.time.minute}",
                        type = "2",
                        id = event.id
                    )

                    scaffoldState.bottomSheetState.collapse()
                    viewModel.onEvent(DoctorSectionEvent.ResetAddState)
                }
                else -> Unit
            }
        }
    }

    BottomSheetScaffold(
        topBar = {
            ReminderHeader(
                title = stringResource(id = R.string.reminder),
                onBackClick = onBackClick,
                pagerState = pagerState
            )
        },
        sheetContent = {
            if (pagerState.currentPage == 0) {
                AddMedicineSheet(
                    state = medicineState.addMedicineState,
                    onEvent = viewModel::onEvent,
                    onCancel = {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }
                )
            }

            if (pagerState.currentPage == 1) {
                AddDoctorSectionSheet(
                    state = doctorState.addDoctorState,
                    onEvent = viewModel::onEvent,
                    onCancel = {
                        coroutineScope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }
                )
            }
        },
        sheetBackgroundColor = Neutral10,
        sheetPeekHeight = 0.dp,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        scaffoldState = scaffoldState
    ) { paddingValues ->
        HorizontalPager(
            count = 2,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = paddingValues
        ) {page ->
            when(page) {
                0 -> {
                    MedicineSection(
                        state = medicineState,
                        onAddReminder = {
                            coroutineScope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    )
                }
                1 -> {
                    DoctorSection(
                        state = doctorState,
                        onAddReminder = {
                            coroutineScope.launch {
                                scaffoldState.bottomSheetState.expand()
                            }
                        }
                    )
                }
            }
        }
    }
}