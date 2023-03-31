package com.example.sos_data.remote.dto.response

import com.example.core.data.remote.dto.general.Meta

@kotlinx.serialization.Serializable
data class SendSosResponse(
    val meta: Meta
)
