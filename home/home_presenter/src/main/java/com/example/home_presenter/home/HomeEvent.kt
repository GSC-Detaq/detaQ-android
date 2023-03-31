package com.example.home_presenter.home

sealed class HomeEvent {
    object ToggleEditContact: HomeEvent()
    data class AddContact(
        val number: String,
        val name: String
    ): HomeEvent()
}