package com.example.profile_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class FamilyResponse(
    val meta: Meta,
    val data: List<Data>
) {
    @kotlinx.serialization.Serializable
    data class Data(
        val uid: String,
        val email: String,
        val name: String
    )
}
