package com.example.profile_data.mapper

import com.example.profile_data.remote.dto.response.FamilyResponse
import com.example.profile_domain.model.Family

fun FamilyResponse.Data.toFamily(): Family {
    return Family(
        uid = uid,
        email = email,
        name = name
    )
}