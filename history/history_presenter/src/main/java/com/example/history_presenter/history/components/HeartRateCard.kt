package com.example.history_presenter.history.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Red10
import com.example.core_ui.ui.theme.Red60
import com.example.history_presenter.R

@Composable
fun HeartRateCard(
    modifier: Modifier = Modifier,
    heartRate: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        val painter = painterResource(id = R.drawable.heart_rate_bg)

        Image(
            painter = painter,
            contentDescription = "heart rate card background",
            modifier = Modifier
                .aspectRatio(painter.intrinsicSize.width / painter.intrinsicSize.height)
                .fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        val heartBpmText = buildAnnotatedString {
            withStyle(
                style = MaterialTheme
                    .typography
                    .h1
                    .copy(
                        color = MaterialTheme.colors.secondary,
                        fontSize = 32.sp
                    )
                    .toSpanStyle()
            ) {
                append(heartRate.toString())
            }

            withStyle(
                style = MaterialTheme
                    .typography
                    .h5
                    .copy(
                        color = Color(0xFF8694CB)
                    )
                    .toSpanStyle()
            ) {
                append("bpm")
            }
        }

        Text(
            text = heartBpmText,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(24.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 24.dp, end = 64.dp)
                .width(33.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Red60,
                            Red10
                        )
                    ),
                    shape = RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 8.dp
                    ),
                    alpha = 0.25f
                )
                .align(Alignment.BottomEnd),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = heartRate.toString(),
                style = MaterialTheme.typography.h3.copy(
                    color = Red60
                )
            )
        }
    }
}

@Preview
@Composable
fun HeartRateCardPreview() {
    DetaQTheme {
        HeartRateCard(heartRate = 0)
    }
}