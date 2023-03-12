package com.example.sos_presenter.countdown_sent.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.PrimaryButton
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Neutral100
import com.example.sos_presenter.R

@Composable
fun SosSection(
    modifier: Modifier = Modifier
) {
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
        Text(
            text = stringResource(id = R.string.sos_sent_title),
            style = MaterialTheme.typography.h1.copy(
                color = Neutral100
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(id = R.string.sos_sent_desc),
            style = MaterialTheme.typography.caption.copy(
                color = Color(0xFF808080)
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        SosCard()

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            textRes = R.string.call_112,
            textModifier = Modifier
                .fillMaxWidth(),
            onClick = {

            }
        )
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

@Preview
@Composable
fun SosCardPreview() {
    DetaQTheme {
        SosCard()
    }
}

@Preview
@Composable
fun SosSectionPreview() {
    DetaQTheme {
        SosSection()
    }
}