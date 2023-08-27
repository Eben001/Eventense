package com.ebenezer.gana.eventense.domain.model

import androidx.annotation.DrawableRes
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class User(
    val id: String,
    val name: String,
    val emailAddress: String,
    val profileImage:String? = null,

    @Transient
    @DrawableRes
    val profilePhotoRes: Int? = null
)
