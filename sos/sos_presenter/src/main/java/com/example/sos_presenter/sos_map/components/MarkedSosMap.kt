package com.example.sos_presenter.sos_map.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MarkedSosMap(
    modifier: Modifier = Modifier,
    location: LatLng?
) {
    val defaultLocation = LatLng(
        -6.2088,
        106.8456
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(defaultLocation, 15f)
    }

    LaunchedEffect(key1 = true) {
        location?.let { location ->
            cameraPositionState.animate(
                update = CameraUpdateFactory.newCameraPosition(
                    CameraPosition(
                        location,
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
        cameraPositionState = cameraPositionState
    ) {
        location?.let {
            Marker(
                state = MarkerState(position = location),
                title = "Sos Location",
                snippet = "Marker in Sos Location"
            )
        }
    }
}