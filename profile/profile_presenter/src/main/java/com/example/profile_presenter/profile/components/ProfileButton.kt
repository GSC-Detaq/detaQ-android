package com.example.profile_presenter.profile.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Neutral40
import com.example.profile_presenter.R

@Composable
fun ProfileButton(
    @DrawableRes iconRes: Int,
    @StringRes textRes: Int,
    modifier: Modifier = Modifier,
    color: Color = Neutral100,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Transparent
        ),
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        modifier = modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = Neutral40,
                shape = RoundedCornerShape(8.dp)
            ),
        enabled = enabled
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = stringResource(id = textRes),
                style = MaterialTheme.typography.button.copy(
                    color = color
                ),
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun ProfileButtonPrev() {
    DetaQTheme {
        ProfileButton(
            iconRes = R.drawable.account_icon,
            textRes = R.string.account
        ) {

        }
    }
}