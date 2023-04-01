package com.example.profile_presenter.connect_wristband

sealed class ConnectWristbandEvent {
    data class OnCodeChange(val code: String): ConnectWristbandEvent()
    object Connect: ConnectWristbandEvent()
}