package com.example.history_presenter.calendar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import java.time.YearMonth

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CalendarPager(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(),
    currentMonth: YearMonth,
    loadNextDates: () -> Unit,
    loadPrevDates: () -> Unit,
    content: @Composable (currentPage: Int) -> Unit
) {
    val pageStart = 60 + currentMonth.month.value
    val lastPage = remember { mutableStateOf(pageStart) }
    val pagerState = rememberPagerState(initialPage = pageStart)
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage > lastPage.value) {
            loadNextDates()
            lastPage.value = pagerState.currentPage
        }
        if (pagerState.currentPage < lastPage.value ) {
            loadPrevDates()
            lastPage.value = pagerState.currentPage
        }
    }
    HorizontalPager(
        count = 120,
        state = pagerState,
        verticalAlignment = Alignment.Top,
        itemSpacing = 16.dp,
        modifier = modifier,
        contentPadding = paddingValues,
        key = { page -> currentMonth.month.name + "-${page}" }
    ) { currentPage ->
        content(currentPage)
    }
}