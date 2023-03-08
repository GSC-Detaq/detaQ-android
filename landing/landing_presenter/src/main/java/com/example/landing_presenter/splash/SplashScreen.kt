package com.example.landing_presenter.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.landing_presenter.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinish: () -> Unit
) {
    val currentOnFinish by rememberUpdatedState(onFinish)

    LaunchedEffect(true) {
        delay(2000)
        currentOnFinish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.detaq_logo),
            contentDescription = "DetaQ Logo",
            modifier = Modifier
                .width(210.dp)
                .height(60.dp)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    DetaQTheme {
        SplashScreen(
            onFinish = {}
        )
    }
}