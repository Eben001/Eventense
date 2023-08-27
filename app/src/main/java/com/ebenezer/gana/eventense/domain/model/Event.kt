package com.ebenezer.gana.eventense.domain.model

data class Event(
    val id: String,
    val title: String, val details: String,
    val attachedImages: List<Int> = emptyList(),
    val hasBeenLiked:Boolean,
    val likes:Int,
    val comments:Int,
    val user:User
)
