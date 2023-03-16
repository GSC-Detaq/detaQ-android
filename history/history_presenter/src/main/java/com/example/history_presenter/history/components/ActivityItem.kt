package com.example.history_presenter.history.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.history_presenter.R
import com.example.history_presenter.history.ActivityRecommendation

@Composable
fun ActivityItem(
    modifier: Modifier = Modifier,
    activity: ActivityRecommendation
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = activity.iconRes),
                contentDescription = "${activity.recommendation}'s icon",
                modifier = Modifier
                    .size(70.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = activity.recommendation,
                    style = MaterialTheme.typography.h4.copy(
                        color = Neutral100
                    )
                )

                Text(
                    text = activity.schedule,
                    style = MaterialTheme.typography.caption.copy(
                        color = Color(0xFFB3B3B3)
                    )
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun ActivityItemPreview() {
    DetaQTheme {
        ActivityItem(
            activity = ActivityRecommendation(
                iconRes = R.drawable.cycling_icon,
                recommendation = "Cycling 30 Minutes",
                schedule = "2-3 Times In A Week"
            )
        )
    }
}