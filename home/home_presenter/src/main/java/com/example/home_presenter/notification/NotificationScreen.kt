package com.example.home_presenter.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.CommonHeader
import com.example.home_presenter.R
import com.example.home_presenter.notification.components.NotificationItem

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = hiltViewModel(),
    onSosClick: (Double, Double) -> Unit,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.notification),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = paddingValues
        ) {
            items(
                items = state.notifications,
                key = { notification -> notification.title }
            ) { notification ->
                NotificationItem(
                    notification = notification,
                    onClick = {
                        onSosClick(
                            notification.lat ?: return@NotificationItem,
                            notification.long ?: return@NotificationItem
                        )
                    }
                )

                Divider()
            }
        }
    }
}