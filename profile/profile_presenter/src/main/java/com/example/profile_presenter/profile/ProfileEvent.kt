package com.example.profile_presenter.profile

sealed class ProfileEvent {
    object LogOut: ProfileEvent()
    data class ToggleDialog(val isShow: Boolean): ProfileEvent()
}