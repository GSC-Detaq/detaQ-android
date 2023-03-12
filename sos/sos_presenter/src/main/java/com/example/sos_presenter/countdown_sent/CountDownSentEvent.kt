package com.example.sos_presenter.countdown_sent

sealed class CountDownSentEvent {
    data class OnSearchTextChange(val text: String): CountDownSentEvent()
}