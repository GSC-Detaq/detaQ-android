package com.example.landing_presenter.onboarding

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.utils.UiEvent
import com.example.landing_presenter.onboarding.components.OnBoardingOne
import com.example.landing_presenter.onboarding.components.OnBoardingThree
import com.example.landing_presenter.onboarding.components.OnBoardingTwo

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
    onFinishClick: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onFinishClick()
                else -> Unit
            }
        }
    }

    AnimatedContent(
        targetState = viewModel.currentPage,
        modifier = Modifier
            .fillMaxSize(),
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = {
                    it
                }
            ) with slideOutHorizontally(
                targetOffsetX = {
                    -it
                }
            )
        }
    ) { page ->
        when (page) {
            OnBoardingPage.One -> {
                OnBoardingOne(
                    onSkip = viewModel::onFinishClick,
                    onNext = {
                        viewModel.onNextClick(OnBoardingPage.Two)
                    }
                )
            }
            OnBoardingPage.Two -> {
                OnBoardingTwo(
                    onSkip = viewModel::onFinishClick,
                    onNext = {
                        viewModel.onNextClick(OnBoardingPage.Three)
                    }
                )
            }
            OnBoardingPage.Three -> {
                OnBoardingThree(
                    onFinish = viewModel::onFinishClick
                )
            }
        }
    }
}