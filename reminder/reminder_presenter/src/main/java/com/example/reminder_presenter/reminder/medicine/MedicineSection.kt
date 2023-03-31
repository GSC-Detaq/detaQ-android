package com.example.reminder_presenter.reminder.medicine

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.OutlinedPrimaryButton
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.reminder_presenter.R
import com.example.reminder_presenter.reminder.medicine.components.MedicineItem
import com.example.reminder_presenter.utils.ReminderReceiver

@Composable
fun MedicineSection(
    state: MedicineSectionState,
    onAddReminder: () -> Unit
) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(
            items = state.medicines,
            key = { medicine -> medicine.reminderId }
        ) { medicine ->
            MedicineItem(medicine = medicine)
        }

        item {
            OutlinedPrimaryButton(
                textRes = R.string.add_reminder,
                textModifier = Modifier
                    .fillMaxWidth(),
                onClick = {
                    if (ReminderReceiver.hasSchedulePermission(context)) {
                        onAddReminder()
                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                            ReminderReceiver.requestSchedulePermission(
                                context,
                                activity
                            )
                        }
                    }
                }
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