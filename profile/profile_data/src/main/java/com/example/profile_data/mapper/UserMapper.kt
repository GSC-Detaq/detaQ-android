package com.example.profile_data.mapper

import com.example.profile_data.remote.dto.response.UserResponse
import com.example.profile_domain.model.User

fun UserResponse.Data.toUser(): User {
    return User(
        email = email,
        name = name,
        roleName = role_name
    )
}