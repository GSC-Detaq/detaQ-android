package com.example.reminder_presenter.reminder.doctor.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
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
import com.example.reminder_presenter.reminder.doctor.AddDoctorState
import com.example.reminder_presenter.reminder.doctor.DoctorSectionEvent
import java.util.*
import com.example.core_ui.R as RCore

@Composable
fun AddDoctorSectionSheet(
    modifier: Modifier = Modifier,
    state: AddDoctorState,
    onEvent: (DoctorSectionEvent) -> Unit,
    onCancel: () -> Unit
) {
    val context = LocalContext.current
    val focus = LocalFocusManager.current

    val dCalendar = Calendar.getInstance()
    val dYear = dCalendar.get(Calendar.YEAR)
    val dMonth = dCalendar.get(Calendar.MONTH)
    val dDay = dCalendar.get(Calendar.DAY_OF_MONTH)

    dCalendar.time = Date()

    val pickDatePickerDialog = DatePickerDialog(
        context,
        RCore.style.Theme_DialogTheme,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            val dateString = if(mDayOfMonth <= 9) "0$mDayOfMonth" else "$mDayOfMonth"
            val month = mMonth + 1
            val monthString = if(month <= 9) "0$month" else "$month"

            onEvent(
                DoctorSectionEvent.OnPickDate(
                    date = "$mYear-$monthString-$dateString".toLocalDate()
                )
            )

            focus.clearFocus()
        },
        dYear,
        dMonth,
        dDay
    )

    val timeCalendar = Calendar.getInstance()
    val tHour = timeCalendar[Calendar.HOUR_OF_DAY]
    val tMinute = timeCalendar[Calendar.MINUTE]

    val timePickerDialog = TimePickerDialog(
        context,
        RCore.style.Theme_DialogTheme,
        {_, mHour : Int, mMinute: Int ->
            onEvent(
                DoctorSectionEvent.OnPickTime(
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
                    text = stringResource(id = R.string.add_doctor_title),
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
                value = state.activity,
                onValueChange = { newText ->
                    onEvent(
                        DoctorSectionEvent.OnActivityChange(newText)
                    )
                },
                label = { Text(text = "Activity") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item {
            OutlinedTextField(
                value = state.doctorName,
                onValueChange = { newText ->
                    onEvent(
                        DoctorSectionEvent.OnDoctorNameChange(newText)
                    )
                },
                label = { Text(text = "Doctor's Name") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }

        item {
            ClickableField(
                title = "Date",
                value = state.date.asString(),
                onClick = {
                    pickDatePickerDialog.show()
                }
            )
        }

        item {
            val stateHour = state.time.hour
            val stateMinute = state.time.minute
            val hour = if (stateHour < 10) "0${stateHour}" else stateHour
            val minute = if (stateMinute < 10) "0${stateMinute}" else stateMinute

            val timeText = if (stateHour >= 12) "$hour.$minute PM" else "$hour.$minute AM"

            ClickableField(
                title = "Time",
                value = if (stateHour == 0) "" else timeText,
                onClick = {
                    timePickerDialog.show()
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
                        DoctorSectionEvent.AddDoctor
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun AddDoctorSheetPreview() {
    DetaQTheme {
        AddDoctorSectionSheet(
            state = AddDoctorState(),
            onEvent = {  },
            onCancel = {  }
        )
    }
}