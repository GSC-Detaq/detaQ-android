package com.example.profile_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class ConnectWristbandResponse(
    val meta: Meta
)
