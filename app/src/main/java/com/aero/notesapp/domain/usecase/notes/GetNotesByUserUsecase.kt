package com.aero.notesapp.domain.usecase.notes

import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.domain.repository.NotesRepository

class GetNotesByUserUsecase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(userId: Int): List<NotesModel>{
        return notesRepository.getNotesByUser(userId = userId)
    }
}