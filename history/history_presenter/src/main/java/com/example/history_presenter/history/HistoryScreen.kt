package com.example.history_presenter.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.CommonHeader
import com.example.core_ui.ui.theme.Neutral100
import com.example.history_presenter.R
import com.example.history_presenter.calendar.CalendarView
import com.example.history_presenter.history.components.ActivityItem
import com.example.history_presenter.history.components.HeartRateCard

@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.history),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                CalendarView(
                    currentMonth = state.currentMonth,
                    selectedDate = state.selectedDate,
                    loadDatesForMonth = {
                        viewModel.onEvent(
                            event = HistoryEvent.LoadNextDates(it)
                        )
                    },
                    onDayClick = {
                        viewModel.onEvent(
                            event = HistoryEvent.SelectDate(it)
                        )
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = stringResource(id = R.string.heart_rate_overview),
                    style = MaterialTheme.typography.h3.copy(
                        color = Neutral100
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(16.dp))

                HeartRateCard(
                    heartRate = state.heartBpm,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = stringResource(id = R.string.heart_rate_overview),
                    style = MaterialTheme.typography.h3.copy(
                        color = Neutral100
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    textAlign = TextAlign.Start
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            items(
                items = state.activities,
                key = { activity -> activity.recommendation }
            ) { activity ->
                ActivityItem(
                    activity = activity,
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }
}