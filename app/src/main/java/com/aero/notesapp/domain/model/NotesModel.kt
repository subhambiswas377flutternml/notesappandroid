package com.aero.notesapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class NotesModel(
    val id:Int,
    val userId: Int,
    val title: String,
    val description: String,
)