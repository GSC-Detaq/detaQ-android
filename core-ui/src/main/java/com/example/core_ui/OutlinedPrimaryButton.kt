package com.example.core_ui

import androidx.annotation.StringRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral10
import com.example.core_ui.ui.theme.Red50

@Composable
fun OutlinedPrimaryButton(
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    textAlign: TextAlign = TextAlign.Center,
    isEnabled: Boolean = true,
    onClick: () -> Unit,
) {
    val localGradient = LocalGradient.current

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent,
            contentColor = Neutral10
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        enabled = isEnabled,
        modifier = modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                BorderStroke(
                    width = 1.dp,
                    brush = localGradient.primary
                ),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = stringResource(id = textRes),
            style = MaterialTheme.typography.button.copy(
                color = Red50
            ),
            modifier = textModifier,
            textAlign = textAlign
        )
    }
}

@Preview
@Composable
fun OutlinedButtonPreview() {
    DetaQTheme {
        OutlinedPrimaryButton(
            textRes = R.string.test,
            textModifier = Modifier
                .fillMaxWidth()
        ) {

        }
    }
}