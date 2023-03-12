package com.example.sos_presenter.countdown

data class CountDownState(
    val countDown: Int = 5,
    val contacts: List<String> = dummyListOfContacts,
    val isCallAmbulance: Boolean = false
)

private val dummyListOfContacts = listOf(
    "Mom",
    "Adam",
    "Grace"
)