package com.example.profile_presenter.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.preferences.TokenPreferences
import com.example.core.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val preferences: Preferences,
    private val tokenPreferences: TokenPreferences
): ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ProfileEvent) {
        when(event) {
            ProfileEvent.LogOut -> {
                preferences.saveShouldShowOnBoarding(true)
                tokenPreferences.setToken("")

                _state.value = state.value.copy(
                    isDialogShow = false
                )

                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Success)
                }
            }
            is ProfileEvent.ToggleDialog -> {
                _state.value = state.value.copy(
                    isDialogShow = event.isShow
                )
            }
        }
    }
}