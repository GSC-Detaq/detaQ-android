package com.example.reminder_domain.utils

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.stream.Collectors
import java.util.stream.LongStream

fun rangeOfDates(
    start: LocalDate,
    end: LocalDate
): List<LocalDate> {
    val days = start.until(
        end,
        ChronoUnit.DAYS
    )

    return LongStream.rangeClosed(0, days)
        .mapToObj(start::plusDays)
        .collect(Collectors.toList())
}