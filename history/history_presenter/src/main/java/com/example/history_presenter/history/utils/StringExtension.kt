package com.example.history_presenter.history.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter

internal fun String.toLocalDate(): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    return LocalDate.parse(this, formatter)
}