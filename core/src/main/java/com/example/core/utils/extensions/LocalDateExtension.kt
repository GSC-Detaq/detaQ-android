package com.example.core.utils.extensions

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDateTime.formattedDate(): String {
    val month = this.month.name.lowercase().replaceFirstChar { it.uppercase() }
    val day = if(this.dayOfMonth < 10) "0${this.dayOfMonth}" else this.dayOfMonth
    val year = this.year

    return "$day $month $year"
}

fun LocalDate.asString(
    dateFormat: String = "dd-MM-yyy"
): String {
    val format = DateTimeFormatter.ofPattern(dateFormat)
    return format.format(this)
}