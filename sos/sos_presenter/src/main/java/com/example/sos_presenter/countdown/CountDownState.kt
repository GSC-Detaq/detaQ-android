package com.example.sos_presenter.countdown

import com.example.core.domain.model.Contact

data class CountDownState(
    val countDown: Int = 5,
    val contacts: List<Contact> = emptyList(),
    val isCallAmbulance: Boolean = false
)