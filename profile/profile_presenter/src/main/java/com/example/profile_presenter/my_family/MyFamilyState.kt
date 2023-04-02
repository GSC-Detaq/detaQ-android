package com.example.profile_presenter.my_family

import com.example.profile_domain.model.Family

data class MyFamilyState(
    val patient: String? = "Darren#8900",
    val families: List<Family> = emptyList(),
    val isEditing: Boolean = false
)