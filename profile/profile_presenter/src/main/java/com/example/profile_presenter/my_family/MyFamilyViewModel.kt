package com.example.profile_presenter.my_family

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.Resource
import com.example.profile_domain.use_cases.GetFamily
import com.example.profile_domain.use_cases.GetPatient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MyFamilyViewModel @Inject constructor(
    private val getFamily: GetFamily,
    private val getPatient: GetPatient
): ViewModel() {
    private val _state = MutableStateFlow(MyFamilyState())
    val state: StateFlow<MyFamilyState> = _state.asStateFlow()

    init {
        initFamily()
        initPatient()
    }

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

    private fun initFamily() {
        viewModelScope.launch {
            when (val result = getFamily()) {
                is Resource.Error -> {
                    Timber.d(result.message)
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        family = result.data ?: emptyList()
                    )
                }
            }
        }
    }

    private fun initPatient() {
        viewModelScope.launch {
            when (val result = getPatient()) {
                is Resource.Error -> {
                    Timber.d(result.message)
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        patients = result.data ?: emptyList()
                    )
                }
            }
        }
    }
}