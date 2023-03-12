package com.example.sos_presenter.sos.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.LocalGradient
import com.example.core_ui.R
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Red50
import com.example.core_ui.ui.theme.Red60

@Composable
fun SosButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val localGradient = LocalGradient.current

    Box(
        modifier = Modifier
            .size(250.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Red50,
                        Red60,
                        Color.Transparent
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        FloatingActionButton(
            modifier = modifier
                .size(200.dp)
                .shadow(
                    elevation = 30.dp,
                    shape = CircleShape,
                    ambientColor = Red50,
                    spotColor = Red50
                )
                .background(
                    localGradient.primary,
                    CircleShape
                ),
            shape = CircleShape,
            onClick = onClick,
            backgroundColor = Color.Transparent,
            elevation = FloatingActionButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                hoveredElevation = 0.dp,
                focusedElevation = 0.dp
            )
        ) {
            Image(
                painter = painterResource(id = R.drawable.sos_icon),
                contentDescription = "SOS",
                modifier = Modifier
                    .size(95.dp)
            )
        }
    }
}

@Preview
@Composable
fun SosButtonPreview() {
    DetaQTheme {
        SosButton(
            onClick = {  }
        )
    }
}