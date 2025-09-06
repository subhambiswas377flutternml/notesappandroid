package com.aero.notesapp.domain.repository

import com.aero.notesapp.core.request.UpdateNoteRequest
import com.aero.notesapp.domain.model.NotesModel

interface NotesRepository {
    suspend fun getNotesByUser(userId: Int): List<NotesModel>
    suspend fun updateNote(noteId:Int, updateNoteRequest: UpdateNoteRequest): List<NotesModel>
}