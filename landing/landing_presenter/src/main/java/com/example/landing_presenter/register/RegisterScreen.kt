package com.example.landing_presenter.register

import androidx.compose.animation.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.core.utils.UiEvent
import com.example.landing_presenter.register.section.CreateAccount
import com.example.landing_presenter.register.section.FillNumber
import com.example.landing_presenter.register.section.NumberVerification
import com.example.landing_presenter.register.section.SelectRole

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    showSnackBar: (String) -> Unit,
    onSignIn: () -> Unit,
    onConfirmClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    var isBack by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onConfirmClick()
                is UiEvent.ShowSnackBar -> showSnackBar(event.message.asString(context))
                else -> Unit
            }
        }
    }

    AnimatedContent(
        targetState = state.currentSection,
        modifier = Modifier
            .fillMaxSize(),
        transitionSpec = {
            slideInHorizontally(
                initialOffsetX = {
                    if (isBack) -it else it
                }
            ) with slideOutHorizontally(
                targetOffsetX = {
                    if (isBack) it else -it
                }
            )
        }
    ) { section ->
        when (section) {
            RegisterSection.CreateAccount -> {
                CreateAccount(
                    state = state,
                    onEvent = { event ->
                        isBack = false
                        viewModel.onEvent(event)
                    },
                    onSignIn = onSignIn
                )
            }
            RegisterSection.FillNumber -> {
                FillNumber(
                    state = state,
                    onEvent = { event ->
                        if (event is RegisterEvent.UpdateSection) {
                            isBack = event.section == RegisterSection.CreateAccount
                        }

                        viewModel.onEvent(event)
                    }
                )
            }
            RegisterSection.NumberVerification -> {
                NumberVerification(
                    state = state,
                    onEvent = { event ->
                        if (event is RegisterEvent.UpdateSection) {
                            isBack = event.section == RegisterSection.FillNumber
                        }

                        if (event is RegisterEvent.OnVerifyOtp) {
                            isBack = false
                        }

                        viewModel.onEvent(event)
                    }
                )
            }
            RegisterSection.SelectRole -> {
                SelectRole(
                    state = state,
                    onEvent = { event ->
                        if (event is RegisterEvent.UpdateSection) {
                            isBack = event.section == RegisterSection.NumberVerification
                        }

                        if (event is RegisterEvent.Register) {
                            isBack = false
                        }

                        viewModel.onEvent(event)
                    }
                )
            }
        }
    }
}