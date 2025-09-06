package com.aero.notesapp.core.request.note

data class UpdateNoteRequest(
    val title: String,
    val description: String,
)