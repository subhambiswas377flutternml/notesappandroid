package com.aero.notesapp.domain.model

data class NotesModel(
    val id:Int,
    val userId: Int,
    val title: String,
    val description: String,
)