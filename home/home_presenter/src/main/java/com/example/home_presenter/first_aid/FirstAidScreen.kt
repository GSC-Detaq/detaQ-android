package com.example.home_presenter.first_aid

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.home_presenter.R
import com.example.home_presenter.first_aid.components.FirstAidHeader
import com.example.home_presenter.components.QuickHelpSection
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun FirstAidScreen(
    viewModel: FirstAidViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val pagerState = rememberPagerState()
    val state by viewModel.state.collectAsState()

    Scaffold(
        topBar = {
            FirstAidHeader(
                title = stringResource(id = R.string.first_aid),
                onBackClick = onBackClick,
                pagerState = pagerState
            )
        }
    ) { paddingValues ->
        HorizontalPager(
            count = 2,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = paddingValues
        ) {page ->
            when(page) {
                0 -> {
                    QuickHelpSection(
                        quickHelps = state.consciousFirstAids
                    )
                }
                1 -> {
                    QuickHelpSection(
                        quickHelps = state.unconsciousFirstAids
                    )
                }
            }
        }
    }
}