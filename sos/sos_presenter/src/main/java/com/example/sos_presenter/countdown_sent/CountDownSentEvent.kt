package com.example.sos_presenter.countdown_sent

import android.location.Location

sealed class CountDownSentEvent {
    data class OnSearchTextChange(val text: String): CountDownSentEvent()
    data class UpdateLocation(val location: Location?): CountDownSentEvent()
    data class SendSos(val location: Location): CountDownSentEvent()
}