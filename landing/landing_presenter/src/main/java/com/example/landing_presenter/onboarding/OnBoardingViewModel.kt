package com.example.landing_presenter.onboarding

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OnBoardingViewModel: ViewModel() {

    var currentPage: OnBoardingPage by mutableStateOf(OnBoardingPage.One)
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNextClick(boarding: OnBoardingPage) {
        currentPage = boarding
    }

    fun onFinishClick() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Success)
        }
    }
}

enum class OnBoardingPage {
    One, Two, Three
}