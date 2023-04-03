package com.example.landing_presenter.register.section

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
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
import com.example.core_ui.ui.theme.Neutral60
import com.example.landing_presenter.R
import com.example.landing_presenter.components.PrimaryButton
import com.example.landing_presenter.register.RegisterEvent
import com.example.landing_presenter.register.RegisterSection
import com.example.landing_presenter.register.RegisterState
import com.example.landing_presenter.register.components.RegisterHeader
import com.example.landing_presenter.register.components.RoleDropDown

@Composable
fun SelectRole(
    state: RegisterState,
    onEvent: (RegisterEvent) -> Unit
) {
    Scaffold(
        topBar = {
            RegisterHeader(
                currentSection = RegisterSection.SelectRole,
                onBack = {
                    onEvent(
                        RegisterEvent.UpdateSection(RegisterSection.NumberVerification)
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
                painter = painterResource(id = R.drawable.register_role),
                contentDescription = "Select Role Icon",
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
                    text = stringResource(id = R.string.select_role_title),
                    style = MaterialTheme.typography.h1.copy(
                        color = Neutral100
                    )
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = stringResource(id = R.string.select_role_desc),
                    style = MaterialTheme.typography.body2.copy(
                        color = Neutral60
                    )
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            RoleDropDown(
                role = state.role,
                isOpen = state.roleDropDownOpen,
                onClick = {
                    onEvent(
                        RegisterEvent.ToggleRoleDropDown(true)
                    )
                },
                onDismiss = {
                    onEvent(
                        RegisterEvent.ToggleRoleDropDown(false)
                    )
                },
                onSelectRole = {
                    onEvent(
                        RegisterEvent.OnPickRole(it)
                    )
                }
            )

//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceAround,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                UserRole.values().forEach { role ->
//                    RoleCard(
//                        role = role,
//                        isSelected = state.role == role,
//                        onClick = {
//                            onEvent(
//                                RegisterEvent.OnPickRole(role)
//                            )
//                        }
//                    )
//                }
//            }

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                text = UiText.StringResource(R.string.confirm),
                textModifier = Modifier
                    .fillMaxWidth(),
                isEnabled = state.confirmationEnabled
            ) {
                onEvent(
                    RegisterEvent.Register
                )
            }
        }
    }
}

@Preview
@Composable
fun SelectRolePreview() {
    DetaQTheme {
        SelectRole(
            state = RegisterState(),
            onEvent = {  }
        )
    }
}