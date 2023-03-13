package com.example.reminder_presenter.reminder.medicine.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.PrimaryButton
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Neutral40
import com.example.reminder_presenter.R
import com.example.reminder_presenter.reminder.medicine.AddMedicineState
import com.example.reminder_presenter.reminder.medicine.MedicineSectionEvent
import com.example.reminder_presenter.utils.asString
import timber.log.Timber
import java.util.*

@Composable
fun AddMedicineSheet(
    modifier: Modifier = Modifier,
    state: AddMedicineState,
    onEvent: (MedicineSectionEvent) -> Unit,
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
                    Timber.d("Pick Date")
                }
            )
        }

        item {
            ClickableField(
                title = "Date End",
                value = state.dateEnd.asString(),
                onClick = {

                }
            )
        }

        item {
            val listOfTimeText = state.time.map { time ->
                val hour = if (time.hour < 10) "0${time.hour}" else time.hour
                val minute = if (time.minute < 10) "0${time.minute}" else time.minute

                if (time.hour > 12) "$hour.$minute PM" else "$hour.$minute AM"
            }

            val textOfListTimeText = listOfTimeText.joinToString(separator = ", ")

            ClickableField(
                title = "Time",
                value = textOfListTimeText,
                onClick = {

                }
            )
        }

        item {
            ClickableField(
                title = "Use Instructions",
                value = "",
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

                }
            )
        }
    }
}

@Composable
fun ClickableField(
    title: String,
    value: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {  },
        label = { Text(text = title) },
        singleLine = true,
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged { focus ->
                if (focus.isFocused) {
                    onClick()
                }
            }
    )
}

@Preview
@Composable
fun ClickableFieldPreview() {
    DetaQTheme {
        ClickableField(
            title = "Date Started",
            value = Date().asString(),
            onClick = {  }
        )
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