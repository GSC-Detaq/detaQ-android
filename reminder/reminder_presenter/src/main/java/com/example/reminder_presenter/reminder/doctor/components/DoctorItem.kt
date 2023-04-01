package com.example.reminder_presenter.reminder.doctor.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.utils.extensions.asString
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Red50
import com.example.reminder_domain.model.DoctorReminder
import com.example.core.domain.model.Time
import com.example.reminder_presenter.R
import java.time.LocalDate

@Composable
fun DoctorItem(
    modifier: Modifier = Modifier,
    doctor: DoctorReminder
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
                painter = painterResource(id = R.drawable.doctor_icon),
                contentDescription = "Doctor Icon",
                modifier = Modifier
                    .size(50.dp)
            )

            Spacer(modifier = Modifier.width(24.dp))

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.CalendarToday,
                        contentDescription = null,
                        tint = Red50,
                        modifier = Modifier
                            .size(14.dp)
                    )
                    
                    Text(
                        text = doctor.date.asString("EEEE, dd MMMM yyy"),
                        style = MaterialTheme.typography.caption.copy(
                            color = Red50
                        )
                    )
                }
                
                Text(
                    text = doctor.doctorName,
                    style = MaterialTheme.typography.h4.copy(
                        color = Neutral100
                    )
                )

                Text(
                    text = stringResource(id = R.string.activity, doctor.activity),
                    style = MaterialTheme.typography.caption.copy(
                        color = Color(0xFFB3B3B3)
                    )
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun DoctorItemPreview() {
    DetaQTheme {
        DoctorItem(
            doctor = DoctorReminder(
                reminderId = "123",
                doctorName = "Dr. Aisyah Jamal",
                activity = "Monthly Control",
                date = LocalDate.now(),
                time = Time(0, 0)
            )
        )
    }
}