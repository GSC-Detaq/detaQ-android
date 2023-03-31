package com.example.sos_presenter.countdown.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core_ui.LocalGradient
import com.example.core_ui.OutlinedPrimaryButton
import com.example.core_ui.PrimaryButton
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.sos_presenter.R
import com.example.sos_presenter.countdown.CountDownEvent
import com.example.sos_presenter.countdown.CountDownState

@OptIn(ExperimentalTextApi::class)
@Composable
fun CountDownSection(
    state: CountDownState,
    onEvent: (CountDownEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val localGradient = LocalGradient.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(id = R.string.keep_calm),
            style = MaterialTheme.typography.h1.copy(
                color = Neutral100
            )
        )

        Text(
            text = stringResource(id = R.string.count_down_desc),
            style = MaterialTheme.typography.caption.copy(
                color = Color(0xFF616161)
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "0${state.countDown}",
            style = MaterialTheme.typography.body1.copy(
                brush = localGradient.primary,
                fontSize = 128.sp
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        PrimaryButton(
            textRes = R.string.skip,
            textModifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onEvent(
                    CountDownEvent.Skip
                )
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedPrimaryButton(
            textRes = R.string.cancel,
            textModifier = Modifier
                .fillMaxWidth(),
            onClick = {
                onEvent(
                    CountDownEvent.Cancel
                )
            }
        )
    }
}

@Preview
@Composable
fun CountDownSectionPreview() {
    DetaQTheme {
        CountDownSection(
            state = CountDownState(),
            onEvent = {  }
        )
    }
}