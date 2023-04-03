package com.example.profile_presenter.connect_family

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.utils.UiEvent
import com.example.core_ui.CommonHeader
import com.example.core_ui.PrimaryButton
import com.example.core_ui.ui.theme.Neutral100
import com.example.core_ui.ui.theme.Neutral60
import com.example.profile_presenter.R
import com.example.profile_presenter.connect_family.components.SearchTextField

@Composable
fun ConnectFamilyScreen(
    viewModel: ConnectFamilyViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackBar -> {
                    showSnackBar(
                        event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.connect_title),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.add_family_title),
                style = MaterialTheme.typography.h3.copy(
                    color = Neutral100
                )
            )

            Text(
                text = stringResource(id = R.string.add_family_desc),
                style = MaterialTheme.typography.body2.copy(
                    color = Neutral60
                )
            )

            SearchTextField(
                text = state.searchText,
                onTextChange = {
                    viewModel.onEvent(
                        event = ConnectFamilyEvent.OnEmailChange(it)
                    )
                },
                error = state.searchError?.message
            )

            PrimaryButton(
                textRes = R.string.add,
                textModifier = Modifier
                    .fillMaxWidth(),
                isEnabled = state.connectEnabled
            ) {
                viewModel.onEvent(
                    event = ConnectFamilyEvent.AddEmail
                )
            }
        }
    }
}