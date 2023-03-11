package com.example.home_presenter.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Red60
import com.example.home_presenter.R

@Composable
fun HealthFacilitiesSection(
    modifier: Modifier = Modifier,
    facilities: List<HealthFacility> = dummyHealthFacilities
) {
    LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(
            items = facilities,
            key = { facility -> facility.name }
        ) { facility ->
            HealthFacilityCard(
                facility = facility
            )
        }
    }
}

@Composable
private fun HealthFacilityCard(
    modifier: Modifier = Modifier,
    facility: HealthFacility
) {
    Box(
        modifier = modifier
            .width(304.dp)
            .height(144.dp)
            .clip(RoundedCornerShape(8.dp))
            .shadow(
                elevation = 5.dp
            )
    ) {
        Image(
            painter = painterResource(id = facility.imageRes), // temporary
            contentDescription = facility.name + "image",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = facility.name,
                style = MaterialTheme.typography.h4.copy(
                    color = Neutral10
                )
            )

            Text(
                text = facility.location,
                style = MaterialTheme.typography.caption.copy(
                    color = Neutral10
                )
            )

            Row(
                modifier = Modifier
                    .background(
                        Neutral10,
                        RoundedCornerShape(4.dp)
                    )
                    .padding(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Location Distance Icon",
                    modifier = Modifier
                        .size(12.dp),
                    tint = Red60
                )

                Text(
                    text = "${facility.distance} km",
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }
}

@Preview
@Composable
fun HealthFacilityCardPreview() {
    DetaQTheme {
        HealthFacilityCard(
            facility = dummyHealthFacilities[1]
        )
    }
}

@Preview
@Composable
fun HealthFacilitiesSectionPreview() {
    DetaQTheme {
        HealthFacilitiesSection()
    }
}

data class HealthFacility(
    val imageRes: Int, // temporary
    val name: String,
    val location: String,
    val distance: Int
)

private val dummyHealthFacilities = listOf(
    HealthFacility(
        imageRes = R.drawable.health_facility_sample,
        name = "Brawijaya Hospital",
        location = "Malang, Jawa Timur",
        distance = 354
    ),
    HealthFacility(
        imageRes = R.drawable.health_facility_sample_2,
        name = "Harapan Bunda Clinic",
        location = "Malang, Jawa Timur",
        distance = 786
    )
)