package com.example.profile_presenter.my_family

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MyFamilyViewModel @Inject constructor(

): ViewModel() {
    private val _state = MutableStateFlow(MyFamilyState())
    val state: StateFlow<MyFamilyState> = _state.asStateFlow()

    fun onEvent(event: MyFamilyEvent) {
        when (event) {
            is MyFamilyEvent.Edit -> {
                _state.value = state.value.copy(
                    isEditing = event.isEdit
                )
            }
            is MyFamilyEvent.OnDelete -> Unit
        }
    }
}