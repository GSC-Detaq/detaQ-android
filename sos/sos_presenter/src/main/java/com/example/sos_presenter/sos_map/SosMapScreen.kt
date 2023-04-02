package com.example.sos_presenter.sos_map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.core_ui.CommonHeader
import com.example.sos_presenter.R
import com.example.sos_presenter.sos_map.components.MarkedSosMap
import com.example.sos_presenter.sos_map.components.SosMapSection
import com.google.android.gms.maps.model.LatLng

@Composable
fun SosMapScreen(
    lat: Double?,
    long: Double?,
    onBackClick: () -> Unit
) {
    val markerLocation = if(lat != null && long != null) LatLng(
        lat, long
    ) else null

    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.sos_map),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MarkedSosMap(
                location = markerLocation
            )

            SosMapSection(
                modifier = Modifier
                    .align(Alignment.BottomCenter),
                latitude = lat,
                longitude = long
            )
        }
    }
}