package com.example.landing_presenter.register.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.landing_presenter.components.BasicTextField
import com.example.landing_presenter.components.PasswordTextField
import com.example.landing_presenter.components.PrimaryButton
import com.example.landing_presenter.register.RegisterEvent
import com.example.landing_presenter.register.RegisterSection
import com.example.landing_presenter.register.RegisterState
import com.example.core_ui.R as RCore

@Composable
fun CreateAccount(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit,
    onSignIn: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        Image(
            painter = painterResource(id = RCore.drawable.detaq_logo),
            contentDescription = "DetaQ Logo",
            modifier = Modifier
                .width(210.dp)
                .height(60.dp)
        )

        Spacer(modifier = Modifier.height(64.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.create_account),
                style = MaterialTheme.typography.h1.copy(
                    color = Neutral100
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(id = R.string.create_account_desc),
                style = MaterialTheme.typography.body2.copy(
                    color = Neutral60
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            label = "Username",
            text = state.name,
            onTextChange = {
                onEvent(
                    RegisterEvent.OnNameChange(it)
                )
            },
            error = state.nameError
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            label = "Email",
            text = state.email,
            onTextChange = {
                onEvent(
                    RegisterEvent.OnEmailChange(it)
                )
            },
            error = state.emailError
        )

        Spacer(modifier = Modifier.height(8.dp))

        PasswordTextField(
            password = state.password,
            onPasswordChange = {
                onEvent(
                    RegisterEvent.OnPasswordChange(it)
                )
            },
            passwordVisible = state.isPasswordVisible,
            showPassword = {
                onEvent(
                    RegisterEvent.ToggleShowPassword
                )
            },
            error = state.passwordError
        )

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = UiText.StringResource(R.string.next),
            textModifier = Modifier
                .fillMaxWidth(),
            isEnabled = state.registerEnabled
        ) {
            onEvent(
                RegisterEvent.UpdateSection(RegisterSection.FillNumber)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.have_acc),
                style = MaterialTheme.typography.body2.copy(
                    color = Neutral50
                )
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.secondary
                ),
                modifier = Modifier
                    .clickable {
                        onSignIn()
                    }
            )
        }
    }
}

@Preview
@Composable
fun CreateAccountPreview() {
    DetaQTheme {
        CreateAccount(
            state = RegisterState(),
            onEvent = {  },
            onSignIn = {  }
        )
    }
}