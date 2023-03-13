package com.example.reminder_presenter.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.asString(): String {
    val format = SimpleDateFormat("dd-MM-yyy", Locale.getDefault())
    return format.format(this)
}