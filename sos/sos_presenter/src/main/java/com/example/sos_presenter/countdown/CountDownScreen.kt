package com.example.sos_presenter.countdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.utils.UiEvent
import com.example.core_ui.CommonHeader
import com.example.core_ui.ui.theme.Red10
import com.example.sos_presenter.R
import com.example.sos_presenter.countdown.components.CountDownSection
import com.example.sos_presenter.countdown.components.EmergencyContactSection

@Composable
fun CountDownScreen(
    viewModel: CountDownViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onCountDownFinish: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onCountDownFinish()
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.emergency),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Red10),
            contentPadding = paddingValues,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            
            item {
                CountDownSection(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                EmergencyContactSection(
                    state = state,
                    onEvent = viewModel::onEvent
                )
            }
        }
    }
}