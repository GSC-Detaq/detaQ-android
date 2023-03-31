package com.example.profile_presenter.profile

import com.example.profile_domain.model.User

data class ProfileState(
    val user: User? = null,
    val isFetchingUser: Boolean = false,
    val isDialogShow: Boolean = false
)