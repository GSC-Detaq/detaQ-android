package com.example.home_presenter.independent_handling

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core_ui.CommonHeader
import com.example.home_presenter.R
import com.example.home_presenter.components.QuickHelpSection

@Composable
fun IndependentHandlingScreen(
    viewModel: IndependentHandlingViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            CommonHeader(
                title = stringResource(id = R.string.independent_handling),
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        QuickHelpSection(
            quickHelps = state.helps,
            modifier = Modifier
                .padding(paddingValues)
        )
    }
}