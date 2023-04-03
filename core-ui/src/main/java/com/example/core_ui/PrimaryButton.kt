package com.example.core_ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral10

@Composable
fun PrimaryButton(
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
                brush = localGradient.primary,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Text(
            text = stringResource(id = textRes),
            style = MaterialTheme.typography.button,
            modifier = textModifier
                .height(ButtonDefaults.MinHeight)
                .wrapContentHeight(Alignment.CenterVertically),
            textAlign = textAlign
        )
    }
}

@Preview
@Composable
fun PrimaryButtonPreview() {
    DetaQTheme {
        PrimaryButton(
            textRes = R.string.test,
            textModifier = Modifier
                .fillMaxWidth()
        ) {

        }
    }
}