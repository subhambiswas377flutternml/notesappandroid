package com.aero.notesapp.domain.usecase.notes

import com.aero.notesapp.core.request.note.AddNoteRequest
import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.domain.repository.NotesRepository

class AddNoteByUserUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(addNoteRequest: AddNoteRequest): List<NotesModel>{
        return notesRepository.addNoteByUser(addNoteRequest=addNoteRequest)
    }
}