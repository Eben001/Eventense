package com.ebenezer.gana.eventense.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UserUpdate(
    val firstName:String,
    val lastName:String
)
