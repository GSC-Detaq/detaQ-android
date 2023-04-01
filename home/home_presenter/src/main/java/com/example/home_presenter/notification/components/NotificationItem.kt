package com.example.home_presenter.notification.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.domain.model.Time
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Neutral60
import com.example.home_presenter.R
import com.example.home_presenter.notification.Notification

@Composable
fun NotificationItem(
    notification: Notification,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val time = notification.time
    val hour = if (time.hour < 10) "0${time.hour}" else time.hour
    val minute = if (time.minute < 10) "0${time.minute}" else time.minute
    val timeText = if (time.hour >= 12) "$hour.$minute PM" else "$hour.$minute AM"

    val clickModifier = if (notification.lat != null && notification.long != null) {
        Modifier
            .clickable {
                onClick()
            }
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(clickModifier)
            .padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = notification.icon),
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
                text = notification.description,
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

            if (notification.lat != null && notification.long != null) {
                Icon(
                    imageVector = Icons.Default.ChevronRight,
                    contentDescription = null,
                    tint = Neutral60
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
                icon = R.drawable.medicine_icon,
                title = "Aspirin",
                description = "Drink your medicine!",
                time = Time(hour = 9, minute = 43)
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
                icon = R.drawable.notif_sos_icon,
                title = "SOS",
                description = "Help!",
                time = Time(hour = 9, minute = 43),
                lat = -6.2088,
                long = 106.8456
            ),
            onClick = {  }
        )
    }
}