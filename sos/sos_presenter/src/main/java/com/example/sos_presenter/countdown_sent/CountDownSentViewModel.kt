package com.example.sos_presenter.countdown_sent

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.core.utils.UiEvent
import com.example.core.utils.UiText
import com.example.sos_domain.use_cases.SendSos
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CountDownSentViewModel @Inject constructor(
    private val sendSos: SendSos
): ViewModel() {
    private val _state = MutableStateFlow(CountDownSentState())
    val state: StateFlow<CountDownSentState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var searchJob: Job? = null

    fun onEvent(event: CountDownSentEvent) {
        when(event) {
            is CountDownSentEvent.OnSearchTextChange -> {
                _state.value = state.value.copy(
                    searchText = event.text
                )

                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                }
            }
            is CountDownSentEvent.UpdateLocation -> {
                _state.value = state.value.copy(
                    userLocation = event.location
                )
            }
            is CountDownSentEvent.SendSos -> {
                viewModelScope.launch {
                    val result = sendSos(
                        lat = event.location.latitude,
                        long = event.location.longitude
                    )

                    when(result) {
                        is Resource.Error -> {
                            Timber.d(result.message)
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.DynamicString(result.data ?: "Sos Send!")
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}