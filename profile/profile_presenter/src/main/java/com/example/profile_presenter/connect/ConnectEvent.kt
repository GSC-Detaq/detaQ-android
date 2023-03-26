package com.example.profile_presenter.connect

sealed class ConnectEvent {
    data class OnUsernameChange(val username: String): ConnectEvent()
    object AddUsername: ConnectEvent()
}