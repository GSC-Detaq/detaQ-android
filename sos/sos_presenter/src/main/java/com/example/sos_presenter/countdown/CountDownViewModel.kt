package com.example.sos_presenter.countdown

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.UiEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CountDownViewModel: ViewModel() {
    private val _state = MutableStateFlow(CountDownState())
    val state: StateFlow<CountDownState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var countDownJob: Job? = null

    init {
        countDown()
    }

    fun onEvent(event: CountDownEvent) {
        when(event) {
            CountDownEvent.Cancel -> {
                countDownJob = null
            }
            is CountDownEvent.RemoveContact -> {
                val newContacts = state
                    .value
                    .contacts
                    .toMutableList()

                newContacts
                    .removeIf { it == event.contact }

                _state.value = state.value.copy(
                    contacts = newContacts.toList()
                )
            }
            CountDownEvent.Skip -> {
                countDownJob = null

                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Success)
                }
            }
            is CountDownEvent.ToggleCallAmbulance -> {
                _state.value = state.value.copy(
                    isCallAmbulance = event.isChecked
                )
            }
        }
    }

    private fun countDown() {
        countDownJob = viewModelScope.launch {
            flow {
                var currentValue = state.value.countDown
                while (currentValue > 0) {
                    delay(1000L)
                    currentValue--
                    emit(currentValue)
                }
            }
                .collect { time ->
                    _state.value = state.value.copy(
                        countDown = time
                    )

                    if (time == 0) {
                        _uiEvent.send(UiEvent.Success)
                    }
                }
        }
    }
}