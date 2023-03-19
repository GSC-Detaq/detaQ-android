package com.example.landing_presenter.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.data.preferences.DefaultPreferences
import com.example.core.domain.preferences.Preferences
import com.example.core.utils.Resource
import com.example.core.utils.UiEvent
import com.example.core.utils.errors.ValidationError
import com.example.landing_domain.use_cases.LandingUseCases
import com.example.landing_domain.use_cases.ValidateEmail
import com.example.landing_domain.use_cases.ValidatePassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val landingUseCases: LandingUseCases,
    private val preferences: Preferences
): ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            LoginEvent.Login -> {
                viewModelScope.launch {
                    val result = landingUseCases.login(
                        email = state.value.email,
                        password = state.value.password
                    )

                    when(result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                loginError = result.message
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            preferences.saveShouldShowOnBoarding(
                                shouldShow = false
                            )
                            _uiEvent.send(UiEvent.Success)
                        }
                    }
                }
            }
            is LoginEvent.OnEmailChange -> {
                _state.value = state.value.copy(
                    email = event.email
                )

                val isValid = landingUseCases.validateEmail(email = event.email)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        emailError = null
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        emailError = isValid.exceptionOrNull() as? ValidationError
                    )
                }
            }
            is LoginEvent.OnPasswordChange -> {
                _state.value = state.value.copy(
                    password = event.password
                )

                val isValid = landingUseCases.validatePassword(password = event.password)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        passwordError = null
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        passwordError = isValid.exceptionOrNull() as? ValidationError
                    )
                }
            }
            LoginEvent.ToggleShowPassword -> {
                _state.value = state.value.copy(
                    isPasswordVisible = !state.value.isPasswordVisible
                )
            }
        }
    }
}