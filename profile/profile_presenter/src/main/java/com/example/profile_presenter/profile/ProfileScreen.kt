package com.example.profile_presenter.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.utils.UiEvent
import com.example.core_ui.NegativeConfirmationDialog
import com.example.core_ui.OutlinedPrimaryButton
import com.example.core_ui.ui.theme.*
import com.example.profile_presenter.R
import com.example.profile_presenter.profile.components.ProfileButton

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onConnectClick: () -> Unit,
    onMyFamilyClick: () -> Unit,
    onConnectWristbandClick: () -> Unit,
    onLogOut: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> {
                    onLogOut()
                }
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Red10),
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colors.secondary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.user?.name?.first()?.uppercase() ?: "",
                            style = MaterialTheme.typography.h1.copy(
                                fontSize = 32.sp,
                                color = Neutral10
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Column(
                        modifier = Modifier
                            .weight(1f),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = if (state.isFetchingUser) "loading..." else state.user?.name ?: "null",
                            style = MaterialTheme.typography.h4.copy(
                                color = Neutral100
                            )
                        )

                        Text(
                            text = if (state.isFetchingUser) "loading..." else state.user?.email ?: "null",
                            style = MaterialTheme.typography.body2.copy(
                                color = Neutral60
                            )
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedPrimaryButton(
                textRes = R.string.connect_btn,
                textModifier = Modifier
                    .fillMaxWidth(),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                onConnectClick()
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.personal),
                style = MaterialTheme.typography.h3.copy(
                    color = Neutral50
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.account_icon,
                textRes = R.string.account,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.my_family_icon,
                textRes = R.string.my_family,
                enabled = true,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                onMyFamilyClick()
            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.wristband_icon,
                textRes = R.string.connect_wristband,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                onConnectWristbandClick()
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.general),
                style = MaterialTheme.typography.h3.copy(
                    color = Neutral50
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.settings_icon,
                textRes = R.string.settings,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.security_icon,
                textRes = R.string.security,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.terms_and_con_icon,
                textRes = R.string.terms_and_con,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.help_icon,
                textRes = R.string.help,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.about_icon,
                textRes = R.string.about,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {

            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ProfileButton(
                iconRes = R.drawable.log_out_icon,
                textRes = R.string.log_out,
                color = Negative60,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            ) {
                viewModel.onEvent(
                    event = ProfileEvent.ToggleDialog(true)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    if (state.isDialogShow) {
        NegativeConfirmationDialog(
            message = "Are You Sure to Log Out?",
            onDismiss = {
                viewModel.onEvent(
                    event = ProfileEvent.ToggleDialog(false)
                )
            },
            onClicked = {
                viewModel.onEvent(
                    event = ProfileEvent.LogOut
                )
            },
            cancellationText = "Cancel",
            confirmationText = "Log Out"
        )
    }
}