package com.example.profile_presenter.connect_family

sealed class ConnectFamilyEvent {
    data class OnEmailChange(val email: String): ConnectFamilyEvent()
    object AddEmail: ConnectFamilyEvent()
}