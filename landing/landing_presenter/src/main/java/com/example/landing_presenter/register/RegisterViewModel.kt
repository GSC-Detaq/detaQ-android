package com.example.landing_presenter.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.utils.Resource
import com.example.core.utils.UiEvent
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
class RegisterViewModel @Inject constructor(
    private val landingUseCases: LandingUseCases,
    private val preferences: Preferences
): ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.OnEmailChange -> {
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
            is RegisterEvent.OnNameChange -> {
                _state.value = state.value.copy(
                    name = event.name
                )
            }
            is RegisterEvent.OnNumberChange -> {
                _state.value = state.value.copy(
                    number = event.number
                )

                val isValid = landingUseCases.validateNumber(number = event.number)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        numberError = null
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        numberError = isValid.exceptionOrNull() as? ValidationError
                    )
                }
            }
            is RegisterEvent.OnPasswordChange -> {
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
            is RegisterEvent.OnPickRole -> {
                _state.value = state.value.copy(
                    role = event.role
                )
            }
            is RegisterEvent.ToggleRoleDropDown -> {
                _state.value = state.value.copy(
                    roleDropDownOpen = event.isOpen
                )
            }
            RegisterEvent.OnSendOtp -> Unit
            is RegisterEvent.OnOtpChange -> {
                _state.value = state.value.copy(
                    otp = event.otp
                )
            }
            RegisterEvent.OnVerifyOtp -> {
                _state.value = state.value.copy(
                    currentSection = RegisterSection.SelectRole
                )
            }
            RegisterEvent.Register -> {
                viewModelScope.launch {
                    val result = landingUseCases.register(
                        email = state.value.email,
                        password = state.value.password,
                        name = state.value.name,
                        roleId = state.value.role?.getRoleId() ?: return@launch
                    )

                    when(result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                registerError = result.message
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
            is RegisterEvent.UpdateSection -> {
                _state.value = state.value.copy(
                    currentSection = event.section
                )
            }
            RegisterEvent.ToggleShowPassword -> {
                _state.value = state.value.copy(
                    isPasswordVisible = !state.value.isPasswordVisible
                )
            }
        }
    }
}