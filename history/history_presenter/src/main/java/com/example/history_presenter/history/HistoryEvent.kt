package com.example.history_presenter.history

import java.time.LocalDate
import java.time.YearMonth

sealed class HistoryEvent {
    class LoadNextDates(
        val month: YearMonth
    ) : HistoryEvent()

    class SelectDate(val date: LocalDate) : HistoryEvent()
}