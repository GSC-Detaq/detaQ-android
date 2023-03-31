package com.example.history_presenter.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.history_domain.use_cases.GetBpm
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getBpm: GetBpm
) : ViewModel() {
    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    init {
        initBpm()
    }

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

    private fun initBpm() {
        viewModelScope.launch {
            getBpm()
                .collect {
                    _state.value = state.value.copy(
                        heartBpm = it.bpm?.toIntOrNull() ?: 0
                    )
                }
        }
    }
}