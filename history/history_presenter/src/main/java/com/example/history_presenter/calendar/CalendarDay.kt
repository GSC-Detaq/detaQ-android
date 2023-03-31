package com.example.history_presenter.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral60
import java.time.LocalDate

@Composable
fun DayView(
    date: LocalDate,
    onDayClick: (LocalDate) -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false
) {
    val isCurrentDay = date == LocalDate.now()
    val dayValueModifier =
        if (isCurrentDay) Modifier
            .background(
            CalendarColor.getBackgroundColor(color = CalendarColor.Today),
            shape = RoundedCornerShape(4.dp)
        )
        else if (isSelected) Modifier.background(
            CalendarColor.getBackgroundColor(color = CalendarColor.Selected),
            shape = RoundedCornerShape(4.dp)
        )
        else Modifier

    Box(
        modifier
            .aspectRatio(1f)
            .clickable { onDayClick(date) },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date.dayOfMonth.toString(),
            style = MaterialTheme.typography.caption.copy(
                color = if (isSelected || isCurrentDay) CalendarColor.getTextColor(color = CalendarColor.Today)
                else Neutral60
            ),
            textAlign = TextAlign.Center,
            modifier = dayValueModifier
                .padding(8.dp)
        )
    }
}

@Preview
@Composable
fun DayViewPreview() {
    DetaQTheme {
        DayView(
            date = LocalDate.now(),
            onDayClick = {  }
        )
    }
}