package com.aero.notesapp.core.request.note

data class AddNoteRequest(
    val userId: Int,
    val title: String,
    val description: String,
)