package com.example.reminder_presenter.reminder.medicine.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.utils.extensions.asString
import com.example.core.utils.extensions.toLocalDate
import com.example.core_ui.PrimaryButton
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Neutral40
import com.example.reminder_presenter.R
import com.example.core.domain.model.Time
import com.example.reminder_presenter.reminder.components.ClickableField
import com.example.reminder_presenter.reminder.medicine.AddMedicineState
import com.example.reminder_presenter.reminder.medicine.MedicineSectionEvent
import java.util.*
import com.example.core_ui.R as RCore

@Composable
fun AddMedicineSheet(
    modifier: Modifier = Modifier,
    state: AddMedicineState,
    onEvent: (MedicineSectionEvent) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current
    val focus = LocalFocusManager.current

    val startCalendar = Calendar.getInstance()
    val startYear = startCalendar.get(Calendar.YEAR)
    val startMonth = startCalendar.get(Calendar.MONTH)
    val startDay = startCalendar.get(Calendar.DAY_OF_MONTH)

    startCalendar.time = Date()

    val startDatePickerDialog = DatePickerDialog(
        context,
        RCore.style.Theme_DialogTheme,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val dateString = if(mDayOfMonth <= 9) "0$mDayOfMonth" else "$mDayOfMonth"
            val month = mMonth + 1
            val monthString = if(month <= 9) "0$month" else "$month"

            onEvent(
                MedicineSectionEvent.OnPickDateStart(
                    date = "$mYear-$monthString-$dateString".toLocalDate()
                )
            )

            focus.clearFocus()
        },
        startYear,
        startMonth,
        startDay
    )

    val endCalendar = Calendar.getInstance()
    val endYear = endCalendar.get(Calendar.YEAR)
    val endMonth = endCalendar.get(Calendar.MONTH)
    val endDay = endCalendar.get(Calendar.DAY_OF_MONTH)

    endCalendar.time = Date()

    val endDatePickerDialog = DatePickerDialog(
        context,
        RCore.style.Theme_DialogTheme,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val dateString = if(mDayOfMonth <= 9) "0$mDayOfMonth" else "$mDayOfMonth"
            val month = mMonth + 1
            val monthString = if(month <= 9) "0$month" else "$month"

            onEvent(
                MedicineSectionEvent.OnPickDateEnd(
                    date = "$mYear-$monthString-$dateString".toLocalDate()
                )
            )

            focus.clearFocus()
        },
        endYear,
        endMonth,
        endDay
    )

    val timeCalendar = Calendar.getInstance()
    val tHour = timeCalendar[Calendar.HOUR_OF_DAY]
    val tMinute = timeCalendar[Calendar.MINUTE]

    val timePickerDialog = TimePickerDialog(
        context,
        RCore.style.Theme_DialogTheme,
        {_, mHour : Int, mMinute: Int ->
            onEvent(
                MedicineSectionEvent.OnAddTime(
                    Time(
                        hour = mHour,
                        minute = mMinute
                    )
                )
            )

            focus.clearFocus()
        },
        tHour,
        tMinute,
        false
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .height(4.dp)
                    .width(64.dp)
                    .background(
                        color = Neutral40,
                        shape = RoundedCornerShape(12.dp)
                    )
            )
        }

        item {
            Row {
                Text(
                    text = stringResource(id = R.string.cancel),
                    style = MaterialTheme.typography.caption.copy(
                        color = MaterialTheme.colors.secondary
                    ),
                    modifier = Modifier
                        .clickable {
                            onCancel()
                        }
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }

        item {
            Spacer(modifier = Modifier)
        }

        item {
            Row {
                Text(
                    text = stringResource(id = R.string.add_medicine_title),
                    style = MaterialTheme.typography.h3.copy(
                        color = Neutral100
                    ),
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        }

        item {
            OutlinedTextField(
                value = state.medicineName,
                onValueChange = { newText ->
                    onEvent(
                        MedicineSectionEvent.OnMedicineNameChange(newText)
                    )
                },
                label = { Text(text = "Medicine's Name") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = state.drugDosage,
                onValueChange = { newText ->
                    onEvent(
                        MedicineSectionEvent.OnDrugDosageChange(newText)
                    )
                },
                label = { Text(text = "Drug Dosage") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.NumberPassword
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item {
            ClickableField(
                title = "Date Start",
                value = state.dateStart.asString(),
                onClick = {
                    startDatePickerDialog.show()
                }
            )
        }

        item {
            ClickableField(
                title = "Date End",
                value = state.dateEnd.asString(),
                onClick = {
                    endDatePickerDialog.show()
                }
            )
        }

        item {
            val listOfTimeText = state.time.map { time ->
                val hour = if (time.hour < 10) "0${time.hour}" else time.hour
                val minute = if (time.minute < 10) "0${time.minute}" else time.minute

                if (time.hour >= 12) "$hour.$minute PM" else "$hour.$minute AM"
            }

            val textOfListTimeText = listOfTimeText.joinToString(separator = ", ")

            ClickableField(
                title = "Time",
                value = textOfListTimeText,
                onClick = {
                    timePickerDialog.show()
                }
            )
        }

        item {
            InstructionDropDown(
                instruction = state.instructions,
                isOpen = state.isInstructionOpen,
                onClick = {
                    onEvent(
                        MedicineSectionEvent.ToggleInstructionDropDown(true)
                    )
                },
                onDismiss = {
                    onEvent(
                        MedicineSectionEvent.ToggleInstructionDropDown(false)
                    )
                },
                onSelectInstruction = {
                    onEvent(
                        MedicineSectionEvent.OnPickInstruction(it)
                    )
                }
            )
        }
        
        item {
            PrimaryButton(
                textRes = R.string.add,
                textModifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    onEvent(
                        MedicineSectionEvent.AddMedicine
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun AddMedicineSheetPreview() {
    DetaQTheme {
        AddMedicineSheet(
            state = AddMedicineState(),
            onEvent = {  },
            onCancel = {  }
        )
    }
}