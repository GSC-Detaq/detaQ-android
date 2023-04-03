package com.example.landing_presenter.register

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
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val landingUseCases: LandingUseCases,
    private val validateEmail: ValidateEmail,
    private val preferences: Preferences
): ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state: StateFlow<RegisterState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var verifyOtpJob: Job? = null

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.OnEmailChange -> {
                _state.value = state.value.copy(
                    email = event.email
                )

                val isValid = validateEmail(email = event.email)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        emailError = null
                    )

                    _state.value = state.value.copy(
                        registerEnabled = isRegisterEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        emailError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        registerEnabled = isRegisterEnabled()
                    )
                }
            }
            is RegisterEvent.OnNameChange -> {
                _state.value = state.value.copy(
                    name = event.name
                )

                _state.value = state.value.copy(
                    registerEnabled = isRegisterEnabled()
                )
            }
            is RegisterEvent.OnNumberChange -> {
                _state.value = state.value.copy(
                    number = event.number,
                    sendOtpError = null
                )

                val isValid = landingUseCases.validateNumber(number = event.number)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        numberError = null
                    )

                    _state.value = state.value.copy(
                        sendOtpEnabled = isSendOtpEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        numberError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        sendOtpEnabled = isSendOtpEnabled()
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

                    _state.value = state.value.copy(
                        registerEnabled = isRegisterEnabled()
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        passwordError = isValid.exceptionOrNull() as? ValidationError
                    )

                    _state.value = state.value.copy(
                        registerEnabled = isRegisterEnabled()
                    )
                }
            }
            is RegisterEvent.OnPickRole -> {
                _state.value = state.value.copy(
                    role = event.role
                )

                _state.value = state.value.copy(
                    confirmationEnabled = isConfirmationEnabled()
                )
            }
            is RegisterEvent.ToggleRoleDropDown -> {
                _state.value = state.value.copy(
                    roleDropDownOpen = event.isOpen
                )
            }
            is RegisterEvent.OnSendOtpResult -> {
                when (val result = event.result) {
                    is Resource.Error -> {
                        Timber.d(result.message)
                        _state.value = state.value.copy(
                            sendOtpError = result.message,
                            sendOtpLoading = false
                        )

                        _state.value = state.value.copy(
                            sendOtpEnabled = isSendOtpEnabled()
                        )

                        viewModelScope.launch {
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.DynamicString(result.message ?: "Unexpected Error")
                                )
                            )
                        }
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            sendOtpLoading = true
                        )

                        _state.value = state.value.copy(
                            sendOtpEnabled = isSendOtpEnabled()
                        )
                    }
                    is Resource.Success -> {
                        if (result.data == null) {
                            _state.value = state.value.copy(
                                sendOtpError = "Unexpected Error",
                                sendOtpLoading = false
                            )

                            _state.value = state.value.copy(
                                sendOtpEnabled = isSendOtpEnabled()
                            )

                            return
                        }

                        if (result.data?.isVerificationCompleted == true) {
                            _state.value = state.value.copy(
                                sendOtpError = null,
                                sendOtpLoading = false,
                                currentSection = RegisterSection.SelectRole
                            )
                        } else {
                            _state.value = state.value.copy(
                                sendOtpError = null,
                                sendOtpLoading = false,
                                currentSection = RegisterSection.NumberVerification,
                                verificationId = result.data?.verificationId
                            )
                        }

                        _state.value = state.value.copy(
                            sendOtpEnabled = isSendOtpEnabled()
                        )
                    }
                }
            }
            is RegisterEvent.OnOtpChange -> {
                _state.value = state.value.copy(
                    otp = event.otp,
                    verifyOtpError = null
                )

                _state.value = state.value.copy(
                    verifyOtpEnabled = isVerifyOtpEnabled()
                )
            }
            RegisterEvent.OnVerifyOtp -> {
                if (state.value.verifyOtpLoading) {
                    return
                }

                verifyOtpJob?.cancel()
                verifyOtpJob = viewModelScope.launch {
                    landingUseCases
                        .verifyOtp(
                            verificationId = state.value.verificationId ?: return@launch,
                            otp = state.value.otp
                        )
                        .collectLatest { result ->
                            when (result) {
                                is Resource.Error -> {
                                    Timber.d(result.message)
                                    _state.value = state.value.copy(
                                        verifyOtpError = result.message,
                                        verifyOtpLoading = false
                                    )

                                    _state.value = state.value.copy(
                                        verifyOtpEnabled = isVerifyOtpEnabled()
                                    )

                                    _uiEvent.send(
                                        UiEvent.ShowSnackBar(
                                            UiText.DynamicString(result.message ?: "Unexpected Error")
                                        )
                                    )
                                }
                                is Resource.Loading -> {
                                    _state.value = state.value.copy(
                                        verifyOtpLoading = true
                                    )

                                    _state.value = state.value.copy(
                                        verifyOtpEnabled = isVerifyOtpEnabled()
                                    )
                                }
                                is Resource.Success -> {
                                    if (result.data == null) {
                                        _state.value = state.value.copy(
                                            verifyOtpError = "Unexpected Error",
                                            verifyOtpLoading = false
                                        )

                                        _state.value = state.value.copy(
                                            verifyOtpEnabled = isVerifyOtpEnabled()
                                        )

                                        return@collectLatest
                                    }

                                    _state.value = state.value.copy(
                                        verifyOtpError = null,
                                        verifyOtpLoading = false,
                                        currentSection = RegisterSection.SelectRole
                                    )

                                    _state.value = state.value.copy(
                                        verifyOtpEnabled = isVerifyOtpEnabled()
                                    )
                                }
                            }
                        }
                }
            }
            RegisterEvent.Register -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        registerLoading = true
                    )

                    _state.value = state.value.copy(
                        confirmationEnabled = isConfirmationEnabled()
                    )

                    val result = landingUseCases.register(
                        email = state.value.email,
                        password = state.value.password,
                        name = state.value.name,
                        roleId = state.value.role?.getRoleId() ?: return@launch
                    )

                    when(result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                registerError = result.message,
                                registerLoading = false
                            )

                            _state.value = state.value.copy(
                                confirmationEnabled = isConfirmationEnabled()
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
                                confirmationEnabled = isConfirmationEnabled(),
                                registerLoading = false
                            )

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

    private fun isRegisterEnabled(): Boolean {
        return state.value.name.isNotEmpty()
                && state.value.nameError == null
                && state.value.email.isNotEmpty()
                && state.value.emailError == null
                && state.value.password.isNotEmpty()
                && state.value.passwordError == null
    }

    private fun isSendOtpEnabled(): Boolean {
        return state.value.number.isNotEmpty()
                && state.value.numberError == null
                && !state.value.sendOtpLoading
                && state.value.sendOtpError == null
    }

    private fun isVerifyOtpEnabled(): Boolean {
        return state.value.otp.isNotEmpty()
                && state.value.otpError == null
                && !state.value.verifyOtpLoading
                && state.value.verifyOtpError == null
    }

    private fun isConfirmationEnabled(): Boolean {
        return state.value.role != null
                && state.value.roleError == null
    }
}