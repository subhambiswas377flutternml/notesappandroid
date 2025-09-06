package com.aero.notesapp.domain.usecase.notes

import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.domain.repository.NotesRepository

class DeleteByNoteIdUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(noteId: Int): List<NotesModel>{
        return notesRepository.deleteByNoteId(noteId=noteId)
    }
}