package com.example.history_presenter.history

import androidx.annotation.DrawableRes
import com.example.history_presenter.R
import java.time.LocalDate
import java.time.YearMonth

data class HistoryState(
    val heartBpm: Int = 0,
    val activities: List<ActivityRecommendation> = dummyRecommendations,
    val selectedDate: LocalDate = LocalDate.now(),
    val currentMonth: YearMonth = YearMonth.now()
)

private val dummyRecommendations = listOf(
    ActivityRecommendation(
        iconRes = R.drawable.cycling_icon,
        recommendation = "Cycling 30 Minutes",
        schedule = "2-3 Times In A Week"
    ),
    ActivityRecommendation(
        iconRes = R.drawable.cycling_icon,
        recommendation = "Cycling 60 Minutes",
        schedule = "1-2 Times In A Week"
    )
)

data class ActivityRecommendation(
    @DrawableRes val iconRes: Int,
    val recommendation: String,
    val schedule: String
)