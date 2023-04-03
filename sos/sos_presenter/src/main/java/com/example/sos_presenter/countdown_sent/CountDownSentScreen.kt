package com.example.sos_presenter.countdown_sent

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.core.utils.UiEvent
import com.example.core_ui.CommonHeader
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Red60
import com.example.sos_presenter.R
import com.example.sos_presenter.countdown_sent.components.SosLocation
import com.example.sos_presenter.countdown_sent.components.SosSection
import com.google.android.gms.location.*

@Composable
fun CountDownSentScreen(
    viewModel: CountDownSentViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    var initSendSos: Boolean by remember {
        mutableStateOf(false)
    }

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    val locationCallback = object : LocationCallback() {
        override fun onLocationResult(p0: LocationResult) {
            for (lo in p0.locations) {
                viewModel.onEvent(
                    event = CountDownSentEvent.UpdateLocation(
                        location = lo
                    )
                )

                if (state.userLocation != null
                    && lo.distanceTo(state.userLocation!!) >= 100
                ) {
                    viewModel.onEvent(
                        event = CountDownSentEvent.SendSos(
                            location = lo
                        )
                    )
                }

                if (!initSendSos) {
                    initSendSos = true

                    viewModel.onEvent(
                        event = CountDownSentEvent.SendSos(
                            location = lo
                        )
                    )
                }
            }
        }
    }

    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    showSnackBar(
                        event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    LaunchedEffect(true) {
        requestLocationPermission(
            context = context,
            activity = activity
        )

        startLocationUpdate(
            context = context,
            fusedLocationClient = fusedLocationClient,
            locationCallback = locationCallback
        )

//        if (
//            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ==
//            PackageManager.PERMISSION_GRANTED
//            && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
//            PackageManager.PERMISSION_GRANTED
//        ) {
//            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
//            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
//                viewModel.onEvent(
//                    event = CountDownSentEvent.UpdateLocation(
//                        location = location
//                    )
//                )
//            }
//        }
    }

    val lifecycleOwner by rememberUpdatedState(LocalLifecycleOwner.current)
    val lifecycle = lifecycleOwner.lifecycle
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    fusedLocationClient.removeLocationUpdates(locationCallback)
                }
                Lifecycle.Event.ON_RESUME -> {
                    startLocationUpdate(
                        context = context,
                        fusedLocationClient = fusedLocationClient,
                        locationCallback = locationCallback
                    )
                }
                Lifecycle.Event.ON_DESTROY -> {
                    fusedLocationClient.removeLocationUpdates(locationCallback)
                }
                Lifecycle.Event.ON_STOP -> {
                    fusedLocationClient.removeLocationUpdates(locationCallback)
                }
                else -> Unit
            }
        }
        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.emergency),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SosLocation(location = state.userLocation)

            TextField(
                value = state.searchText,
                onValueChange = {
                    viewModel.onEvent(
                        CountDownSentEvent.OnSearchTextChange(it)
                    )
                },
                placeholder = {
                    Text(text = "Your Location...")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location Icon",
                        tint = Red60
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Neutral100,
                    backgroundColor = Neutral10,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                keyboardOptions = KeyboardOptions.Default,
                shape = RoundedCornerShape(8.dp),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.TopCenter)
            )

            SosSection(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

private fun startLocationUpdate(
    context: Context,
    fusedLocationClient: FusedLocationProviderClient,
    locationCallback: LocationCallback
) {
    if (
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) ==
        PackageManager.PERMISSION_GRANTED
        && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) ==
        PackageManager.PERMISSION_GRANTED
    ) {
        val locationRequest = LocationRequest
            .Builder(
                Priority.PRIORITY_HIGH_ACCURACY,
                1000
            )
            .setWaitForAccurateLocation(true)
            .setMinUpdateIntervalMillis(250)
            .build()

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
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