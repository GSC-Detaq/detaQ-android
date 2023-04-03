package com.example.profile_presenter.connect_wristband

data class ConnectWristbandState(
    val code: String = "",
    val connectLoading: Boolean = false,
    val connectEnabled: Boolean = false,
    val connectError: String? = null
)