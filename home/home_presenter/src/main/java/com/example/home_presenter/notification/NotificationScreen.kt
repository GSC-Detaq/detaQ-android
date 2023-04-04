package com.example.home_presenter.notification

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.core.utils.UiEvent
import com.example.core_ui.CommonHeader
import com.example.home_presenter.R
import com.example.home_presenter.notification.components.NotificationItem

@Composable
fun NotificationScreen(
    viewModel: NotificationViewModel = hiltViewModel(),
    onReminderClick: () -> Unit,
    onSosClick: (Double, Double) -> Unit,
    onBackClick: () -> Unit
) {
    val notifications = viewModel.notifications.collectAsLazyPagingItems()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> {
                    notifications.refresh()
                }
                else -> Unit
            }
        }
    }

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
                items = notifications,
                key = { notification -> notification.notificationId }
            ) { notification ->
                notification?.let {
                    NotificationItem(
                        notification = notification,
                        onClick = {
                            viewModel.onEvent(
                                event = NotificationEvent.OnOpenNotification(
                                    notificationId = notification.notificationId
                                )
                            )

                            if (notification.lat != null && notification.long != null) {
                                onSosClick(
                                    notification.lat ?: 0.0,
                                    notification.long ?: 0.0
                                )
                            } else {
                                onReminderClick()
                            }
                        }
                    )

                    Divider()
                }
            }
        }
    }
}