package com.aero.notesapp.domain.repository

import com.aero.notesapp.core.request.note.AddNoteRequest
import com.aero.notesapp.core.request.note.UpdateNoteRequest
import com.aero.notesapp.domain.model.NotesModel

interface NotesRepository {
    suspend fun getNotesByUser(userId: Int): List<NotesModel>
    suspend fun updateNote(noteId:Int, updateNoteRequest: UpdateNoteRequest): List<NotesModel>
    suspend fun addNoteByUser(addNoteRequest: AddNoteRequest): List<NotesModel>
    suspend fun deleteByNoteId(noteId: Int): List<NotesModel>
    suspend fun fetchNotesFromLocal(): List<NotesModel>
}