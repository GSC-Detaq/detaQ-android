package com.example.landing_presenter.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.utils.UiText
import com.example.core_ui.LocalGradient
import com.example.landing_presenter.components.PrimaryButton
import com.example.core_ui.ui.theme.*
import com.example.landing_presenter.R

@Composable
fun OnBoardingOne(
    onSkip: () -> Unit,
    onNext: () -> Unit
) {
    val localGradient = LocalGradient.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Red10),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = painterResource(id = R.drawable.onboarding1),
            contentDescription = "On Boarding 1 Image",
            modifier = Modifier
                .size(300.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .clip(
                    RoundedCornerShape(
                        topStart = 48.dp,
                        topEnd = 48.dp
                    )
                )
                .background(Neutral10)
                .padding(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(32.dp)
                        .height(8.dp)
                        .clip(RectangleShape)
                        .clip(
                            RoundedCornerShape(26.dp)
                        )
                        .background(localGradient.primary)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Neutral30)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(CircleShape)
                        .background(Neutral30)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(id = R.string.onboarding1_title),
                style = MaterialTheme.typography.h1.copy(
                    color = Red80
                )
            )
            
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.onboarding1_desc),
                style = MaterialTheme.typography.body1.copy(
                    color = Neutral60
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { onSkip() }
                ) {
                    Text(
                        text = stringResource(id = R.string.skip),
                        style = MaterialTheme.typography.button.copy(
                            color = Red60
                        )
                    )
                }

                PrimaryButton(
                    text = UiText.StringResource(R.string.next)
                ) {
                    onNext()
                }
            }
        }
    }
}

@Preview
@Composable
fun OnBoardingOnePreview() {
    DetaQTheme {
        OnBoardingOne(
            onSkip = {  },
            onNext = {  }
        )
    }
}