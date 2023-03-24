package com.example.reminder_presenter.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun LocalDate.asString(
    dateFormat: String = "dd-MM-yyy"
): String {
    val format = DateTimeFormatter.ofPattern(dateFormat)
    return format.format(this)
}