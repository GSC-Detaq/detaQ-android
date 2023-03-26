package com.example.profile_presenter.connect

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ConnectViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(ConnectState())
    val state: StateFlow<ConnectState> = _state.asStateFlow()

    fun onEvent(event: ConnectEvent) {
        when(event) {
            ConnectEvent.AddUsername -> Unit
            is ConnectEvent.OnUsernameChange -> {
                _state.value = state.value.copy(
                    searchText = event.username
                )
            }
        }
    }
}