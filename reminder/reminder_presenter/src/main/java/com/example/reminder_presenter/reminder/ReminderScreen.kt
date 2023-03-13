package com.example.reminder_presenter.reminder

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.ui.theme.Neutral10
import com.example.reminder_presenter.R
import com.example.reminder_presenter.reminder.components.ReminderHeader
import com.example.reminder_presenter.reminder.doctor.DoctorSection
import com.example.reminder_presenter.reminder.doctor.components.AddDoctorSectionSheet
import com.example.reminder_presenter.reminder.medicine.MedicineSection
import com.example.reminder_presenter.reminder.medicine.components.AddMedicineSheet
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