package com.example.home_presenter.home

import com.example.core.domain.model.Contact
import com.example.home_presenter.home.components.Article
import com.example.home_presenter.home.components.HealthFacility

data class HomeState(
    val emergencyContacts: List<Contact> = emptyList(),
    val healthFacilities: List<HealthFacility> = emptyList(),
    val articles: List<Article> = emptyList(),
    val notificationCount: Int = 0,
    val isEditingContact: Boolean = false,
    val isInsertingContact: Boolean = false,
    val insertContactError: String? = null
)