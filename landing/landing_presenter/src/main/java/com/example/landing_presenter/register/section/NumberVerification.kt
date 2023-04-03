package com.example.landing_presenter.register.section

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.core.utils.UiText
import com.example.core_ui.ui.theme.DetaQTheme
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Neutral50
import com.example.core_ui.ui.theme.Neutral60
import com.example.landing_presenter.R
import com.example.landing_presenter.components.PrimaryButton
import com.example.landing_presenter.register.RegisterEvent
import com.example.landing_presenter.register.RegisterSection
import com.example.landing_presenter.register.RegisterState
import com.example.landing_presenter.register.components.OtpTextField
import com.example.landing_presenter.register.components.RegisterHeader
import com.example.landing_presenter.register.utils.sendOtp

@Composable
fun NumberVerification(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit
) {
    val activity = LocalContext.current as Activity

    Scaffold(
        topBar = {
            RegisterHeader(
                currentSection = RegisterSection.NumberVerification,
                onBack = {
                    onEvent(
                        RegisterEvent.UpdateSection(RegisterSection.FillNumber)
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
                painter = painterResource(id = R.drawable.register_otp),
                contentDescription = "OTP Icon",
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
                    text = stringResource(id = R.string.register_otp_title),
                    style = MaterialTheme.typography.h1.copy(
                        color = Neutral100
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(id = R.string.register_otp_desc, state.number),
                    style = MaterialTheme.typography.body2.copy(
                        color = Neutral60
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            OtpTextField(
                otpText = state.otp,
                onOtpTextChange = {
                    onEvent(
                        RegisterEvent.OnOtpChange(it)
                    )
                },
                error = state.otpError
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = UiText.StringResource(R.string.submit),
                textModifier = Modifier
                    .fillMaxWidth(),
                isEnabled = state.verifyOtpEnabled
            ) {
                onEvent(
                    RegisterEvent.OnVerifyOtp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.dont_receive_otp),
                    style = MaterialTheme.typography.body2.copy(
                        color = Neutral50
                    )
                )

                Spacer(modifier = Modifier.width(2.dp))

                Text(
                    text = stringResource(id = R.string.resend),
                    style = MaterialTheme.typography.h5.copy(
                        color = MaterialTheme.colors.secondary
                    ),
                    modifier = Modifier
                        .clickable {
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
                )
            }
        }
    }
}

@Preview
@Composable
fun NumberVerificationPreview() {
    DetaQTheme {
        NumberVerification(
            state = RegisterState(),
            onEvent = {  }
        )
    }
}