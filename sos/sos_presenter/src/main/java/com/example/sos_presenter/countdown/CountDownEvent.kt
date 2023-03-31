package com.example.sos_presenter.countdown

sealed class CountDownEvent {
    object Skip: CountDownEvent()
    object Cancel: CountDownEvent()
    data class RemoveContact(val contact: String): CountDownEvent()
    data class ToggleCallAmbulance(val isChecked: Boolean): CountDownEvent()
}