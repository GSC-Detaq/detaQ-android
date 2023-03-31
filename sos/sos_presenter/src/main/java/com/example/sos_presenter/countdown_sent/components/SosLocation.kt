package com.example.sos_presenter.countdown_sent.components

import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun SosLocation(
    modifier: Modifier = Modifier,
    location: Location
) {
    val userLocation = LatLng(
        location.latitude,
        location.longitude
    )
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(userLocation, 15f)
    }

    GoogleMap(
        modifier = modifier
            .fillMaxSize(),
        cameraPositionState = cameraPositionState
    )
//    {
//        Marker(
//            state = MarkerState(position = userLocation),
//            title = "Sos Location",
//            snippet = "Marker in Sos Location"
//        )
//    }
}