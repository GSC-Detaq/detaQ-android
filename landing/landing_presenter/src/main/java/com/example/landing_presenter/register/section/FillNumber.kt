package com.example.landing_presenter.register.section

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.utils.UiText
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Neutral60
import com.example.landing_presenter.R
import com.example.landing_presenter.components.BasicTextField
import com.example.landing_presenter.components.PrimaryButton
import com.example.landing_presenter.register.RegisterEvent
import com.example.landing_presenter.register.RegisterSection
import com.example.landing_presenter.register.RegisterState
import com.example.landing_presenter.register.components.RegisterHeader
import com.example.landing_presenter.register.utils.sendOtp

@Composable
fun FillNumber(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit
) {
    val activity = LocalContext.current as Activity

    Scaffold(
        topBar = {
            RegisterHeader(
                currentSection = RegisterSection.FillNumber,
                onBack = {
                    onEvent(
                        RegisterEvent.UpdateSection(RegisterSection.CreateAccount)
                    )
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Image(
                painter = painterResource(id = R.drawable.register_phone),
                contentDescription = "Phone Icon",
                modifier = Modifier
                    .size(200.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = stringResource(id = R.string.register_phone_title),
                    style = MaterialTheme.typography.h1.copy(
                        color = Neutral100
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(id = R.string.register_phone_desc),
                    style = MaterialTheme.typography.body2.copy(
                        color = Neutral60
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            BasicTextField(
                label = "Phone Number",
                text = state.number,
                onTextChange = {
                    onEvent(
                        RegisterEvent.OnNumberChange(it)
                    )
                },
                error = state.numberError,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = UiText.StringResource(R.string.get_otp),
                textModifier = Modifier
                    .fillMaxWidth(),
                isEnabled = state.sendOtpEnabled
            ) {
                sendOtp(
                    number = state.number,
                    activity = activity,
                    enabled = !state.sendOtpLoading,
                    onResult = {
                        onEvent(
                            RegisterEvent.OnSendOtpResult(it)
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun FillNumberPreview() {
    DetaQTheme {
        FillNumber(
            state = RegisterState(),
            onEvent = {  }
        )
    }
}