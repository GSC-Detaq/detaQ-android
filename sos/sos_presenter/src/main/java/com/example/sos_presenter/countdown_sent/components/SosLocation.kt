package com.example.sos_presenter.countdown_sent.components

import android.location.Location
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun SosLocation(
    modifier: Modifier = Modifier,
    location: Location?
) {
    val defaultLocation = LatLng(
        -6.2088,
        106.8456
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 15f)
    }

    LaunchedEffect(key1 = location) {
        location?.let { location ->
            val userLocation = LatLng(
                location.latitude,
                location.longitude
            )

            cameraPositionState.animate(
                update = CameraUpdateFactory.newCameraPosition(
                    CameraPosition(
                        userLocation,
                        12.5f,
                        0f,
                        0f
                    )
                ),
                durationMs = 1000
            )
        }
    }

    GoogleMap(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(0.75f),
        cameraPositionState = cameraPositionState,
        properties = MapProperties(isMyLocationEnabled = location != null)
    )
//    {
//        Marker(
//            state = MarkerState(position = userLocation),
//            title = "Sos Location",
//            snippet = "Marker in Sos Location"
//        )
//    }
}