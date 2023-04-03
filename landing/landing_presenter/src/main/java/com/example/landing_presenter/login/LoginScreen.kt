package com.example.landing_presenter.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.utils.UiEvent
import com.example.core.utils.UiText
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Neutral50
import com.example.core_ui.ui.theme.Neutral60
import com.example.landing_presenter.R
import com.example.landing_presenter.components.BasicTextField
import com.example.landing_presenter.components.PasswordTextField
import com.example.landing_presenter.components.PrimaryButton
import com.example.core_ui.R as RCore

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit,
    onForgotPassword: () -> Unit,
    onSignUp: () -> Unit,
    onLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onLogin()
                is UiEvent.ShowSnackBar -> showSnackBar(event.message.asString(context))
                else -> Unit
            }
        }
    }

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
                text = stringResource(id = R.string.login_welcome),
                style = MaterialTheme.typography.h1.copy(
                    color = Neutral100
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(id = R.string.login_desc),
                style = MaterialTheme.typography.body2.copy(
                    color = Neutral60
                )
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            label = "Email",
            text = state.email,
            onTextChange = {
                viewModel.onEvent(
                    LoginEvent.OnEmailChange(it)
                )
            },
            error = state.emailError
        )

        Spacer(modifier = Modifier.height(8.dp))

        PasswordTextField(
            password = state.password,
            onPasswordChange = {
                viewModel.onEvent(
                    LoginEvent.OnPasswordChange(it)
                )
            },
            passwordVisible = state.isPasswordVisible,
            showPassword = {
                viewModel.onEvent(
                    LoginEvent.ToggleShowPassword
                )
            },
            error = state.passwordError
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {
           Spacer(modifier = Modifier.weight(1f))

            Text(
                text = stringResource(id = R.string.forgot_password),
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.secondary
                ),
                modifier = Modifier
                    .clickable {
                        onForgotPassword()
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        PrimaryButton(
            text = UiText.StringResource(R.string.login),
            textModifier = Modifier
                .fillMaxWidth(),
            isEnabled = state.loginButtonEnabled
        ) {
            viewModel.onEvent(
                LoginEvent.Login
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.dont_have_acc),
                style = MaterialTheme.typography.body2.copy(
                    color = Neutral50
                )
            )

            Spacer(modifier = Modifier.width(2.dp))

            Text(
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.h5.copy(
                    color = MaterialTheme.colors.secondary
                ),
                modifier = Modifier
                    .clickable {
                        onSignUp()
                    }
            )
        }
    }
}