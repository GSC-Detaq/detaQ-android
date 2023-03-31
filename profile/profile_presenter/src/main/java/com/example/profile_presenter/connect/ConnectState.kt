package com.example.profile_presenter.connect

import com.example.core.utils.errors.ValidationError

data class ConnectState(
    val searchText: String = "",
    val searchError: ValidationError? = null
)