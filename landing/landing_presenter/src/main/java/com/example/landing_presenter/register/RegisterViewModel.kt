package com.example.landing_presenter.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.use_cases.ValidateEmail
import com.example.core.utils.Resource
import com.example.core.utils.UiEvent
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

    private var sendOtpJob: Job? = null
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
            RegisterEvent.OnSendOtp -> {
                if (state.value.sendOtpLoading) {
                    return
                }

                sendOtpJob?.cancel()
                sendOtpJob = viewModelScope.launch {
                    landingUseCases
                        .sendOtp(
                            number = state.value.number
                        )
                        .collectLatest { result ->
                            when (result) {
                                is Resource.Error -> {
                                    Timber.d(result.message)
                                    _state.value = state.value.copy(
                                        sendOtpError = result.message,
                                        sendOtpLoading = false
                                    )
                                }
                                is Resource.Loading -> {
                                    _state.value = state.value.copy(
                                        sendOtpLoading = true
                                    )
                                }
                                is Resource.Success -> {
                                    if (result.data == null) {
                                        _state.value = state.value.copy(
                                            sendOtpError = "Unexpected Error",
                                            sendOtpLoading = false
                                        )

                                        return@collectLatest
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
                                }
                            }
                        }
                }
            }
            is RegisterEvent.OnOtpChange -> {
                _state.value = state.value.copy(
                    otp = event.otp
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
                                }
                                is Resource.Loading -> {
                                    _state.value = state.value.copy(
                                        verifyOtpLoading = true
                                    )
                                }
                                is Resource.Success -> {
                                    if (result.data == null) {
                                        _state.value = state.value.copy(
                                            verifyOtpError = "Unexpected Error",
                                            verifyOtpLoading = false
                                        )

                                        return@collectLatest
                                    }

                                    _state.value = state.value.copy(
                                        verifyOtpError = null,
                                        verifyOtpLoading = false,
                                        currentSection = RegisterSection.SelectRole
                                    )
                                }
                            }
                        }
                }
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