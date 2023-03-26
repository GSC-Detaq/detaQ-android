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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.core.utils.UiEvent
import com.example.core_ui.NegativeConfirmationDialog
import com.example.core_ui.OutlinedPrimaryButton
import com.example.core_ui.ui.theme.*
import com.example.profile_presenter.R
import com.example.profile_presenter.profile.components.ProfileButton

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onConnectClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> Unit
                else -> Unit
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp)
                    .background(Red10),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop,
                    model = ImageRequest
                        .Builder(context)
                        .data(state.user?.profilePic)
                        .crossfade(true)
                        .build(),
                    contentDescription = "User Profile Picture"
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = state.user?.name ?: "null",
                        style = MaterialTheme.typography.h4.copy(
                            color = Neutral100
                        )
                    )

                    Text(
                        text = state.user?.email ?: "null",
                        style = MaterialTheme.typography.body2.copy(
                            color = Neutral60
                        )
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedPrimaryButton(
                textRes = R.string.connect_btn,
                textModifier = Modifier
                    .fillMaxWidth()
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
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.account_icon,
                textRes = R.string.account
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.my_family_icon,
                textRes = R.string.connect_btn,
                enabled = false
            ) {

            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.general),
                style = MaterialTheme.typography.h3.copy(
                    color = Neutral50
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.settings_icon,
                textRes = R.string.settings
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.security_icon,
                textRes = R.string.security
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.terms_and_con_icon,
                textRes = R.string.terms_and_con
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.help_icon,
                textRes = R.string.help
            ) {

            }

            Spacer(modifier = Modifier.height(8.dp))

            ProfileButton(
                iconRes = R.drawable.about_icon,
                textRes = R.string.about
            ) {

            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))

            ProfileButton(
                iconRes = R.drawable.log_out_icon,
                textRes = R.string.log_out,
                color = Negative60
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