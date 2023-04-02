package com.example.home_domain.model

import java.time.LocalDateTime

data class Notification(
    val notificationId: String,
    val title: String,
    val body: String,
    val additionalLink: String,
    val date: LocalDateTime,
    val opened: Boolean,
    val uid: String,
    val type: String,
    val lat: Double?,
    val long: Double?
)
