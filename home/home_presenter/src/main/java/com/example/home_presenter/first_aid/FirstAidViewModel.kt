package com.example.home_presenter.first_aid

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FirstAidViewModel: ViewModel() {
    private val _state = MutableStateFlow(FirstAidState())
    val state: StateFlow<FirstAidState> = _state.asStateFlow()
}