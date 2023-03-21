package com.example.history_presenter.history

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HistoryViewModel : ViewModel() {
    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    fun onEvent(event: HistoryEvent) {
        when(event) {
            is HistoryEvent.LoadNextDates -> {
                _state.value = state.value.copy(
                    currentMonth = event.month
                )
            }
            is HistoryEvent.SelectDate -> {
                _state.value = state.value.copy(
                    selectedDate = event.date
                )
            }
        }
    }
}