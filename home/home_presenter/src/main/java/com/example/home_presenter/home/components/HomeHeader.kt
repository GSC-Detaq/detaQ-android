package com.example.home_presenter.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Red60
import com.example.core_ui.R as RCore

@Composable
fun HomeHeader(
    notificationCounts: Int,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        contentPadding = PaddingValues(start = 16.dp),
        backgroundColor = Neutral10,
        contentColor = Neutral100
    ) {
        Image(
            painter = painterResource(id = RCore.drawable.detaq_logo),
            contentDescription = "DetaQ Logo",
            modifier = Modifier
                .height(32.dp)
                .width(112.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        HomeNotificationButton(
            notificationCounts = notificationCounts,
            onNotificationClick = onNotificationClick
        )
    }
}

@Composable
private fun HomeNotificationButton(
    modifier: Modifier = Modifier,
    notificationCounts: Int,
    onNotificationClick: () -> Unit
) {
    IconButton(
        onClick = onNotificationClick,
        modifier = modifier
    ) {
        Box {
            Icon(
                imageVector = Icons.Outlined.Notifications,
                contentDescription = "Notification Icon",
                modifier = Modifier
                    .size(24.dp)
            )

            if (notificationCounts > 0) {
                val notificationText = if (notificationCounts > 9) "9+" else "$notificationCounts"

                Box(
                    modifier = Modifier
                        .size(14.dp)
                        .clip(CircleShape)
                        .background(Red60)
                        .align(Alignment.TopEnd),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = notificationText,
                        style = MaterialTheme.typography.caption.copy(
                            color = Neutral10,
                            fontSize = 8.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeNotificationButtonWithNotificationPreview() {
    DetaQTheme {
        HomeNotificationButton(
            notificationCounts = 20,
            onNotificationClick = {  }
        )
    }
}

@Preview
@Composable
fun HomeNotificationButtonPreview() {
    DetaQTheme {
        HomeNotificationButton(
            notificationCounts = 0,
            onNotificationClick = {  }
        )
    }
}

@Preview
@Composable
fun HomeHeaderPreview() {
    DetaQTheme {
        HomeHeader(
            notificationCounts = 1,
            onNotificationClick = {  }
        )
    }
}