package com.example.profile_presenter.connect_wristband

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.core.utils.UiEvent
import com.example.core.utils.UiText
import com.example.profile_domain.use_cases.ConnectWristband
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ConnectWristbandViewModel @Inject constructor(
    private val connectWristband: ConnectWristband
) : ViewModel() {
    private val _state = MutableStateFlow(ConnectWristbandState())
    val state: StateFlow<ConnectWristbandState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ConnectWristbandEvent) {
        when (event) {
            is ConnectWristbandEvent.OnCodeChange -> {
                _state.value = state.value.copy(
                    code = event.code,
                    connectError = null
                )

                _state.value = state.value.copy(
                    connectEnabled = isConnectEnabled()
                )
            }
            ConnectWristbandEvent.Connect -> {
                if (state.value.connectLoading) {
                    return
                }

                viewModelScope.launch {
                    _state.value = state.value.copy(
                        connectLoading = true
                    )

                    _state.value = state.value.copy(
                        connectEnabled = isConnectEnabled()
                    )

                    val result = connectWristband(
                        code = state.value.code
                    )

                    when (result) {
                        is Resource.Error -> {
                            Timber.d(result.message)

                            _state.value = state.value.copy(
                                connectError = result.message,
                                connectLoading = false
                            )

                            _state.value = state.value.copy(
                                connectEnabled = isConnectEnabled()
                            )

                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.DynamicString(
                                        result.message ?: "Unexpected Error"
                                    )
                                )
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                code = "",
                                connectLoading = false,
                                connectEnabled = false
                            )

                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.DynamicString(
                                        result.data ?: "Connect Wristband Succeed"
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    private fun isConnectEnabled(): Boolean {
        return state.value.code.isNotEmpty()
                && state.value.connectError == null
                && !state.value.connectLoading
    }
}