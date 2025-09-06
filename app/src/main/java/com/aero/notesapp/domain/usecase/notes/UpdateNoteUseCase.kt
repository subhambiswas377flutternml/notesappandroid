package com.aero.notesapp.domain.usecase.notes

import com.aero.notesapp.core.request.UpdateNoteRequest
import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.domain.repository.NotesRepository

class UpdateNoteUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(noteId: Int, updateNoteRequest: UpdateNoteRequest): List<NotesModel>{
        return notesRepository.updateNote(noteId=noteId, updateNoteRequest=updateNoteRequest)
    }
}