package com.aero.notesapp.core.request

data class UpdateNoteRequest(
    val title: String,
    val description: String,
)