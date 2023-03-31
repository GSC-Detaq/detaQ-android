package com.example.profile_presenter.my_family

data class MyFamilyState(
    val patient: String? = dummyPatient,
    val family: List<String> = dummyFamily,
    val isEditing: Boolean = false
)

private val dummyPatient = "Darren#8900"
private val dummyFamily = listOf(
    "Fahmi#1",
    "Dea#2",
    "Itsar#3"
)