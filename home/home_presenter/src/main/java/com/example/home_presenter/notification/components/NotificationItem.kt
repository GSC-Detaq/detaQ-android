package com.example.home_presenter.notification.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CircleNotifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.model.Time
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Neutral60
import com.example.core_ui.ui.theme.Red60
import com.example.home_domain.model.Notification
import com.example.home_presenter.R
import java.time.LocalDateTime

@Composable
fun NotificationItem(
    notification: Notification,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val time = Time(
        hour = notification.date.hour,
        minute = notification.date.minute
    )
    val hour = if (time.hour < 10) "0${time.hour}" else time.hour
    val minute = if (time.minute < 10) "0${time.minute}" else time.minute
    val timeText = if (time.hour >= 12) "$hour.$minute PM" else "$hour.$minute AM"

    val icon = when (notification.type) {
        "SOS" -> R.drawable.notif_sos_icon
        "Medicine" -> R.drawable.medicine_icon
        "Doctor" -> R.drawable.doctor_icon
        else -> R.drawable.default_icon
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp),
            tint = Color.Black
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = notification.title,
                style = MaterialTheme.typography.h4.copy(
                    color = Neutral100
                )
            )

            Text(
                text = notification.body,
                style = MaterialTheme.typography.body2.copy(
                    color = Neutral60
                )
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = timeText,
                style = MaterialTheme.typography.caption.copy(
                    color = Neutral60
                )
            )

            Spacer(modifier = Modifier.height(2.dp))

            if (!notification.opened) {
                Icon(
                    imageVector = Icons.Default.CircleNotifications,
                    contentDescription = null,
                    tint = Red60
                )
            }
        }
    }
}

@Preview
@Composable
fun MedicineNotificationItemPreview() {
    DetaQTheme {
        NotificationItem(
            notification = Notification(
                notificationId = "1",
                title = "Aspirin",
                body = "Drink your medicine!",
                additionalLink = "",
                date = LocalDateTime.now(),
                opened = false,
                uid = "1",
                type = "Medicine",
                lat = null,
                long = null
            ),
            onClick = {  }
        )
    }
}

@Preview
@Composable
fun SosNotificationItemPreview() {
    DetaQTheme {
        NotificationItem(
            notification = Notification(
                notificationId = "2",
                title = "SOS",
                body = "Help!",
                additionalLink = "",
                date = LocalDateTime.now(),
                opened = false,
                uid = "2",
                type = "SOS",
                lat = -6.2088,
                long = 106.8456
            ),
            onClick = {  }
        )
    }
}