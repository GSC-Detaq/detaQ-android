package com.example.home_presenter.independent_handling

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class IndependentHandlingViewModel: ViewModel() {
    private val _state = MutableStateFlow(IndependentHandlingState())
    val state: StateFlow<IndependentHandlingState> = _state.asStateFlow()
}