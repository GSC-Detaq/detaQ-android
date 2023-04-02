package com.example.home_data.mapper

import com.example.home_data.remote.dto.response.NotificationResponse
import com.example.home_domain.model.Notification
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

fun NotificationResponse.Data.toNotification(): Notification {
    val timestamp = timestamp.toLongOrNull() ?: System.currentTimeMillis()
    val date = LocalDateTime
        .ofInstant(
            Instant.ofEpochMilli(timestamp),
            ZoneId.systemDefault()
        )

    return Notification(
        notificationId = notification_id,
        title = title,
        body = body,
        additionalLink = additional_link,
        date = date,
        opened = clicked,
        uid = uid,
        type = notif_type,
        lat = lat.toDoubleOrNull(),
        long = long.toDoubleOrNull()
    )
}