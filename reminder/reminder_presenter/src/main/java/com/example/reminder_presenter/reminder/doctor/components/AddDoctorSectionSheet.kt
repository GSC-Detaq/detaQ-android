package com.example.reminder_presenter.reminder.doctor.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.PrimaryButton
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Neutral40
import com.example.reminder_presenter.R
import com.example.reminder_presenter.reminder.components.ClickableField
import com.example.reminder_presenter.reminder.doctor.AddDoctorState
import com.example.reminder_presenter.reminder.doctor.DoctorSectionEvent
import com.example.reminder_presenter.utils.asString
import timber.log.Timber

@Composable
fun AddDoctorSectionSheet(
    modifier: Modifier = Modifier,
    state: AddDoctorState,
    onEvent: (DoctorSectionEvent) -> Unit,
    onCancel: () -> Unit
) {
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
                    Timber.d("Pick Date")
                }
            )
        }

        item {
            val hour = if (state.hour < 10) "0${state.hour}" else state.hour
            val minute = if (state.minute < 10) "0${state.minute}" else state.minute

            val timeText = if (state.hour > 12) "$hour.$minute PM" else "$hour.$minute AM"

            ClickableField(
                title = "Time",
                value = if (state.hour == 0) "" else timeText,
                onClick = {

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

                    onCancel()
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