package com.example.sos_presenter.countdown_sent

import android.location.Location

data class CountDownSentState(
    val searchText: String = "",
    val userLocation: Location? = null
)