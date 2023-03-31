package com.example.reminder_presenter.reminder.medicine.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalGradient
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Neutral100
import com.example.reminder_domain.model.MedicineReminder
import com.example.reminder_presenter.R
import java.time.DayOfWeek
import java.time.LocalDate

@Composable
fun MedicineItem(
    modifier: Modifier = Modifier,
    medicine: MedicineReminder
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.medicine_icon),
                contentDescription = "Medicine Icon",
                modifier = Modifier
                    .size(50.dp)
            )
            
            Spacer(modifier = Modifier.width(24.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
//                WeekMarker(
//                    reminderDay = medicine.
//                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = medicine.medicineName,
                    style = MaterialTheme.typography.h4.copy(
                        color = Neutral100
                    )
                )

                Text(
                    text = stringResource(id = R.string.drug_dosage, medicine.medicineDosage),
                    style = MaterialTheme.typography.caption.copy(
                        color = Color(0xFFB3B3B3)
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    medicine.time.forEach { time ->
                        TimeItem(time = time)
                    }
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null
            )
        }
    }
}

@Composable
fun WeekMarker(
    modifier: Modifier = Modifier,
    reminderDay: List<DayOfWeek>
) {
    val localGradient = LocalGradient.current

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        DayOfWeek.values().forEach { day ->
            if (reminderDay.contains(day)) {
                Text(
                    text = day.name.first().uppercase(),
                    style = MaterialTheme.typography.caption.copy(
                        color = Neutral10
                    ),
                    modifier = Modifier
                        .background(
                            brush = localGradient.primary,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(8.dp)
                )
            } else {
                Text(
                    text = day.name.first().uppercase(),
                    style = MaterialTheme.typography.caption.copy(
                        color = Color(0xFF616161)
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun TimeItem(
    modifier: Modifier = Modifier,
    time: MedicineReminder.Time
) {
    val timeModifier = if (time.afterEat) {
        modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(8.dp)
            )
    } else {
        modifier
            .background(
                color = MaterialTheme.colors.secondary,
                shape = RoundedCornerShape(8.dp)
            )
    }

    val hour = if (time.hour < 10) "0${time.hour}" else time.hour
    val minute = if (time.minute < 10) "0${time.minute}" else time.minute
    val timeText = if (time.hour >= 12) "$hour.$minute PM" else "$hour.$minute AM"

    Box(
        modifier = timeModifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = timeText,
            style = MaterialTheme.typography.caption.copy(
                color = if (time.afterEat) MaterialTheme.colors.secondary else Neutral10
            ),
            modifier = Modifier
                .padding(4.dp)
        )
    }
}

@Preview
@Composable
fun TimeItemAfterEatPreview() {
    DetaQTheme {
        TimeItem(
            time = MedicineReminder.Time(
                hour = 9,
                minute = 10,
                afterEat = true
            )
        )
    }
}

@Preview
@Composable
fun TimeItemPreview() {
    DetaQTheme {
        TimeItem(
            time = MedicineReminder.Time(
                hour = 9,
                minute = 10,
                afterEat = false
            )
        )
    }
}

@Preview
@Composable
fun WeekMarkerPreview() {
    DetaQTheme {
        WeekMarker(
            reminderDay = listOf(
                DayOfWeek.MONDAY,
                DayOfWeek.SATURDAY
            )
        )
    }
}

@Preview
@Composable
fun MedicineItemPreview() {
    DetaQTheme {
        MedicineItem(
            medicine = MedicineReminder(
                reminderId = "123",
                medicineName = "Morphin",
                medicineDosage = 2,
                dates = listOf(LocalDate.now()),
                time = listOf(
                    MedicineReminder.Time(
                        hour = 9,
                        minute = 0,
                        afterEat = false
                    ),
                    MedicineReminder.Time(
                        hour = 18,
                        minute = 0,
                        afterEat = true
                    )
                )
            )
        )
    }
}