package com.aero.notesapp.domain.repository

import com.aero.notesapp.domain.model.NotesModel

interface NotesRepository {
    suspend fun getNotesByUser(userId: Int): List<NotesModel>
}