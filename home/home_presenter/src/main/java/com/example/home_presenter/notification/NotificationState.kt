package com.example.home_presenter.notification

import androidx.annotation.DrawableRes
import com.example.core.domain.model.Time
import com.example.home_presenter.R

data class NotificationState(
    val notifications: List<Notification> = dummyNotification
)

data class Notification(
    @DrawableRes val icon: Int,
    val title: String,
    val description: String,
    val time: Time,
    val lat: Double? = null,
    val long: Double? = null
)

private val dummyNotification = listOf(
    Notification(
        icon = R.drawable.medicine_icon,
        title = "Aspirin",
        description = "Drink your medicine!",
        time = Time(hour = 9, minute = 43)
    ),
    Notification(
        icon = R.drawable.notif_sos_icon,
        title = "SOS",
        description = "Help!",
        time = Time(hour = 9, minute = 43),
        lat = -6.2088,
        long = 106.8456
    )
)