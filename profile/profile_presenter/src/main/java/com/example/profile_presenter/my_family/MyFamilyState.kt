package com.example.profile_presenter.my_family

import com.example.profile_domain.model.Family

data class MyFamilyState(
    val patients: List<Family> = emptyList(),
    val family: List<Family> = emptyList(),
    val isEditing: Boolean = false
)