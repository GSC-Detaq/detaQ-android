package com.example.landing_presenter.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.use_cases.ValidateEmail
import com.example.core.utils.Resource
import com.example.core.utils.UiEvent
import com.example.core.utils.UiText
import com.example.core.utils.errors.ValidationError
import com.example.landing_domain.use_cases.LandingUseCases
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
    private val validateEmail: ValidateEmail,
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

                            _state.value = state.value.copy(
                                loginButtonEnabled = isButtonEnabled()
                            )

                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.DynamicString(result.message ?: "Unexpected Error")
                                )
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                loginButtonEnabled = isButtonEnabled()
                            )

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
                    email = event.email,
                    loginError = null
                )

                val isValid = validateEmail(email = event.email)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        emailError = null
                    )

                    _state.value = state.value.copy(
                        loginButtonEnabled = isButtonEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        emailError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        loginButtonEnabled = isButtonEnabled()
                    )
                }
            }
            is LoginEvent.OnPasswordChange -> {
                _state.value = state.value.copy(
                    password = event.password,
                    loginError = null
                )

                val isValid = landingUseCases.validatePassword(password = event.password)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        passwordError = null
                    )

                    _state.value = state.value.copy(
                        loginButtonEnabled = isButtonEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        passwordError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        loginButtonEnabled = isButtonEnabled()
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

    private fun isButtonEnabled(): Boolean {
        return state.value.email.isNotEmpty()
                && state.value.emailError == null
                && state.value.password.isNotEmpty()
                && state.value.passwordError == null
                && state.value.loginError == null
    }
}