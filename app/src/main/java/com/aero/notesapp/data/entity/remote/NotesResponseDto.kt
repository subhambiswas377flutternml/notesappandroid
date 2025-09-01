package com.aero.notesapp.data.entity.remote

import com.aero.notesapp.data.entity.local.NotesLocalEntity

class NotesResponseDto (
    val status: Int,
    val message: String,
    val notes: List<NotesDto>,
)

data class NotesDto (
    val id:Int,
    val userId: Int,
    val title: String,
    val description: String,
)

fun NotesResponseDto.toLocalEntity(): List<NotesLocalEntity>{
    return this.notes.map { element->
        NotesLocalEntity(
            id = element.id,
            userId = element.userId,
            title = element.title,
            description = element.description
        )
    }.toList()
}
