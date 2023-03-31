package com.example.sos_presenter.countdown

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.use_cases.GetContacts
import com.example.core.utils.Resource
import com.example.core.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CountDownViewModel @Inject constructor(
    private val getContacts: GetContacts
): ViewModel() {
    private val _state = MutableStateFlow(CountDownState())
    val state: StateFlow<CountDownState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var countDownJob: Job? = null

    init {
        countDown()
        initGetContacts()
    }

    fun onEvent(event: CountDownEvent) {
        when(event) {
            CountDownEvent.Cancel -> {
                countDownJob?.cancel()
            }
            is CountDownEvent.RemoveContact -> {
                val newContacts = state
                    .value
                    .contacts
                    .toMutableList()

                newContacts
                    .removeIf { it.id == event.contact }

                _state.value = state.value.copy(
                    contacts = newContacts.toList()
                )
            }
            CountDownEvent.Skip -> {
                countDownJob?.cancel()

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
        countDownJob?.cancel()
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

    private fun initGetContacts() {
        viewModelScope.launch {

            when(val result = getContacts()) {
                is Resource.Error -> {
                    Timber.d(result.message)
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        contacts = result.data ?: emptyList()
                    )
                }
            }
        }
    }
}