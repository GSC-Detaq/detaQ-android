package com.example.core.utils.extensions

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toLocalDate(
    pattern: String = "yyyy-MM-dd"
): LocalDate {
    val formatter = DateTimeFormatter.ofPattern(pattern)

    return LocalDate.parse(this, formatter)
}