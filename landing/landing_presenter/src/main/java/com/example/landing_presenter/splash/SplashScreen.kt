package com.example.landing_presenter.splash

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinish: () -> Unit
) {
    val currentOnFinish by rememberUpdatedState(onFinish)
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    LaunchedEffect(true) {
        requestLocationPermission(
            context = context,
            activity = activity
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPushNotificationPermission(
                context = context,
                activity = activity
            )
        }

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

fun hasLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
            && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
            PackageManager.PERMISSION_GRANTED
}

fun requestLocationPermission(context: Context, activity: Activity) {
    if (!hasLocationPermission(context)) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION),
            1
        )
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun hasPushNotificationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) ==
            PackageManager.PERMISSION_GRANTED
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun requestPushNotificationPermission(context: Context, activity: Activity) {
    if (!hasPushNotificationPermission(context)) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
            1
        )
    }
}