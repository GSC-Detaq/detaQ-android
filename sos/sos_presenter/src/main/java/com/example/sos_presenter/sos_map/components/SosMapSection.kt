package com.example.sos_presenter.sos_map.components

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.core_ui.OutlinedPrimaryButton
import com.example.core_ui.PrimaryButton
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Neutral100
import com.example.sos_presenter.R

@Composable
fun SosMapSection(
    modifier: Modifier = Modifier,
    latitude: Double?,
    longitude: Double?
) {
    val context = LocalContext.current
    val activity = LocalContext.current as Activity

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topStart = 48.dp,
                    topEnd = 48.dp
                )
            )
            .background(Neutral10)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SosCard()

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            textRes = R.string.call_112,
            textModifier = Modifier
                .fillMaxWidth(),
            onClick = {
                if (hasContactPermission(context)) {
                    val callIntent = Intent().apply {
                        action = Intent.ACTION_DIAL
                        data = Uri.parse("tel:" + "112")
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    }

                    context.startActivity(callIntent)
                } else {
                    requestContactPermission(context, activity)
                }
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedPrimaryButton(
            textRes = R.string.open_map,
            textModifier = Modifier
                .fillMaxWidth(),
            isEnabled = latitude != null && longitude != null
        ) {
            if (latitude != null && longitude != null) {
                val gmmIntentUri = Uri.parse("geo:0,0?q=$latitude,$longitude(Sos Location)")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")

                context.startActivity(mapIntent)
            }
        }
    }
}

@Composable
fun SosCard(
    modifier: Modifier = Modifier
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
                painter = painterResource(id = R.drawable.sos_sent_icon),
                contentDescription = "Sos Icon",
                modifier = Modifier
                    .size(70.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(id = R.string.call_ambulance_sub),
                    style = MaterialTheme.typography.body2.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Neutral100
                    )
                )

                Text(
                    text = stringResource(id = R.string.sos_call_ambulance_desc),
                    style = MaterialTheme.typography.caption.copy(
                        color = Color(0xFFB3B3B3)
                    )
                )
            }
        }
    }
}

fun hasContactPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) ==
            PackageManager.PERMISSION_GRANTED
}

fun requestContactPermission(context: Context, activity: Activity) {
    if (!hasContactPermission(context)) {
        ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.CALL_PHONE), 1)
    }
}

@Preview
@Composable
fun SosCardPreview() {
    DetaQTheme {
        SosCard()
    }
}

@Preview
@Composable
fun SosMapSectionPreview() {
    DetaQTheme {
        SosMapSection(
            latitude = null,
            longitude = null
        )
    }
}