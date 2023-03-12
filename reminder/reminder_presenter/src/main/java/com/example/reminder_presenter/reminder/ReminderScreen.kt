package com.example.reminder_presenter.reminder

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.reminder_presenter.R
import com.example.reminder_presenter.reminder.components.ReminderHeader
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ReminderScreen(
    onBackClick: () -> Unit
) {
    val pagerState = rememberPagerState()

    Scaffold(
        topBar = {
            ReminderHeader(
                title = stringResource(id = R.string.reminder),
                onBackClick = onBackClick,
                pagerState = pagerState
            )
        }
    ) { paddingValues ->
        HorizontalPager(
            count = 2,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = paddingValues
        ) {page ->
            when(page) {
                0 -> {

                }
                1 -> {

                }
            }
        }
    }
}