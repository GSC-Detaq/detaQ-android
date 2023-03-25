package com.example.history_presenter.calendar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.utils.extensions.toLocalDate
import com.example.core_ui.ui.theme.DetaQTheme
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun CalendarView(
    modifier: Modifier = Modifier,
    currentMonth: YearMonth,
    selectedDate: LocalDate,
    loadDatesForMonth: (YearMonth) -> Unit,
    onDayClick: (LocalDate) -> Unit
) {
    CalendarPager(
        modifier = modifier,
        paddingValues = PaddingValues(horizontal = 16.dp),
        currentMonth = currentMonth,
        loadNextDates = { loadDatesForMonth(currentMonth.plusMonths(1)) },
        loadPrevDates = { loadDatesForMonth(currentMonth.minusMonths(1)) }
    ) { currentPage ->
        val page = currentPage % 12
        val monthOfPage = if (page == 0) 12 else page
        val currentMonthValue = currentMonth.month.value
        val monthToDisplay = if(monthOfPage > currentMonthValue) {
            currentMonth.plusMonths((monthOfPage-currentMonthValue).toLong())
        } else if (monthOfPage < currentMonthValue){
            currentMonth.minusMonths((currentMonthValue-monthOfPage).toLong())
        } else {
            currentMonth
        }
        val startDate = monthToDisplay.atDay(1)
        val dateCount = monthToDisplay.lengthOfMonth()
        val dateToAppend = startDate.dayOfWeek.value - 1
        val dateToPrepend = 7 - ((dateCount + dateToAppend) % 7)
        val totalDates = dateCount + dateToAppend + dateToPrepend
        val year = monthToDisplay.year
        val month = monthToDisplay.month.value
        val rowMoreThan5 = (totalDates/7) > 5

        Column {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                elevation = 5.dp,
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = monthToDisplay
                            .month
                            .name
                            .lowercase()
                            .replaceFirstChar { it.uppercase() },
                        style = MaterialTheme.typography.body2
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    val calendarHeight = if(rowMoreThan5) 355.dp else 328.dp

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.Top),
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        modifier = Modifier.height(calendarHeight),
                        contentPadding = PaddingValues(8.dp)
                    ) {
                        items(
                            (1..totalDates).toList()
                        ) { i ->
                            Column {
                                if(i <= 7) {
                                    Text(
                                        text = DayOfWeek
                                            .of(i)
                                            .name
                                            .substring(0, 2)
                                            .lowercase()
                                            .replaceFirstChar { it.uppercase() },
                                        style = MaterialTheme.typography.caption.copy(
                                            color = Color(0xFFB3B3B3)
                                        ),
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                }

                                if (i > dateToAppend && i <= (dateCount + dateToAppend)) {
                                    val date = i - dateToAppend
                                    val dateString = if(date <= 9) "0$date" else date
                                    val monthString = if(month <= 9) "0$month" else month
                                    val localDate = "$year-$monthString-$dateString".toLocalDate()

                                    DayView(
                                        date = localDate,
                                        onDayClick = onDayClick,
                                        isSelected = localDate == selectedDate
                                    )
                                }
                            }
                        }
                    }
                }
            }

            if (!rowMoreThan5) {
                Spacer(modifier = Modifier.height((355-328).dp))
            }
        }
    }
}

@Preview
@Composable
fun CalenderViewPreview() {
    DetaQTheme {
        CalendarView(
            currentMonth = YearMonth.now(),
            selectedDate = LocalDate.now().minusDays(1),
            loadDatesForMonth = {  },
            onDayClick = {  }
        )
    }
}