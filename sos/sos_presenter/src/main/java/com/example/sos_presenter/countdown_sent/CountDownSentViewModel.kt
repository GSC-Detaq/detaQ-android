package com.example.sos_presenter.countdown_sent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sos_presenter.countdown.CountDownState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountDownSentViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _state = MutableStateFlow(CountDownSentState())
    val state: StateFlow<CountDownSentState> = _state.asStateFlow()

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
        }
    }
}