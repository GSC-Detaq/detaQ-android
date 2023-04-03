package com.example.profile_presenter.connect_family

import com.example.core.utils.errors.ValidationError

data class ConnectFamilyState(
    val searchText: String = "",
    val searchError: ValidationError? = null,
    val connectLoading: Boolean = false,
    val connectError: String? = null,
    val connectEnabled: Boolean = false
)