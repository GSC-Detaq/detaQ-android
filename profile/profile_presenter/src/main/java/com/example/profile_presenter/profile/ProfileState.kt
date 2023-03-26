package com.example.profile_presenter.profile

data class ProfileState(
    val user: User? = null,
    val isDialogShow: Boolean = false
)

data class User(
    val name: String,
    val email: String,
    val profilePic: String
)