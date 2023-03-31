package com.example.profile_presenter.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.preferences.Preferences
import com.example.core.domain.preferences.TokenPreferences
import com.example.core.utils.Resource
import com.example.core.utils.UiEvent
import com.example.profile_domain.use_cases.GetUserPersonal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val preferences: Preferences,
    private val tokenPreferences: TokenPreferences,
    private val getUserPersonal: GetUserPersonal
): ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        initUser()
    }

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

    private fun initUser() {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isFetchingUser = true
            )

            when(val result = getUserPersonal()) {
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isFetchingUser = false
                    )

                    Timber.d(result.message)
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        user = result.data,
                        isFetchingUser = false
                    )
                }
            }
        }
    }
}