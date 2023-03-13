package com.example.reminder_presenter.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.asString(
    dateFormat: String = "dd-MM-yyy"
): String {
    val format = SimpleDateFormat(dateFormat, Locale.getDefault())
    return format.format(this)
}