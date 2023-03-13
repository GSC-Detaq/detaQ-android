package com.example.reminder_presenter.reminder.medicine

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.OutlinedPrimaryButton
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.reminder_presenter.R
import com.example.reminder_presenter.reminder.medicine.components.MedicineItem

@Composable
fun MedicineSection(
    state: MedicineSectionState,
    onAddReminder: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = state.medicines,
            key = { medicine -> medicine.name }
        ) { medicine ->
            MedicineItem(medicine = medicine)
        }

        item {
            OutlinedPrimaryButton(
                textRes = R.string.add_reminder,
                textModifier = Modifier
                    .fillMaxWidth(),
                onClick = onAddReminder
            )
        }
    }
}

@Preview
@Composable
fun MedicineSectionPreview() {
    DetaQTheme {
        MedicineSection(
            state = MedicineSectionState(),
            onAddReminder = {  }
        )
    }
}