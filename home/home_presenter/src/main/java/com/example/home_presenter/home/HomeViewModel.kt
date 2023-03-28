package com.example.home_presenter.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.use_cases.CoreUseCases
import com.example.core.utils.Resource
import com.example.core.utils.UiEvent
import com.example.core.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coreUseCases: CoreUseCases
): ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.ToggleEditContact -> {
                _state.value = state.value.copy(
                    isEditingContact = !state.value.isEditingContact
                )
            }
            is HomeEvent.AddContact -> {
                viewModelScope.launch {
                    _state.value = state.value.copy(
                        isInsertingContact = true
                    )

                    val result = coreUseCases.insertContact(
                        number = event.number,
                        name = event.name
                    )

                    when(result) {
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                insertContactError = result.message,
                                isInsertingContact = false
                            )
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                insertContactError = null,
                                isInsertingContact = false
                            )

                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.DynamicString("Add Contact Success!")
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}