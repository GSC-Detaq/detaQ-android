package com.example.profile_presenter.connect_family

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.use_cases.ValidateEmail
import com.example.core.utils.Resource
import com.example.core.utils.UiEvent
import com.example.core.utils.UiText
import com.example.core.utils.errors.ValidationError
import com.example.profile_domain.use_cases.AddNewFamily
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
class ConnectFamilyViewModel @Inject constructor(
    private val addNewFamily: AddNewFamily,
    private val validateEmail: ValidateEmail
): ViewModel() {
    private val _state = MutableStateFlow(ConnectFamilyState())
    val state: StateFlow<ConnectFamilyState> = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ConnectFamilyEvent) {
        when(event) {
            ConnectFamilyEvent.AddEmail -> {
                viewModelScope.launch {
                    val result = addNewFamily(
                        email = state.value.searchText
                    )

                    when (result) {
                        is Resource.Error -> {
                            Timber.d(result.message)
                        }
                        is Resource.Loading -> Unit
                        is Resource.Success -> {
                            _uiEvent.send(
                                UiEvent.ShowSnackBar(
                                    UiText.DynamicString(
                                        result.data ?: "Add New Family Succeed"
                                    )
                                )
                            )
                        }
                    }
                }
            }
            is ConnectFamilyEvent.OnEmailChange -> {
                _state.value = state.value.copy(
                    searchText = event.email
                )

                val isValid = validateEmail(email = event.email)

                if (isValid.isSuccess) {
                    _state.value = state.value.copy(
                        searchError = null
                    )
                }

                if (isValid.isFailure) {
                    _state.value = state.value.copy(
                        searchError = isValid.exceptionOrNull() as? ValidationError
                    )
                }
            }
        }
    }
}