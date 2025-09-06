package com.aero.notesapp.domain.usecase.notes

import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.domain.repository.NotesRepository

class FetchNotesFromLocalUseCase(private val notesRepository: NotesRepository) {
    suspend operator fun invoke(): List<NotesModel>{
        return notesRepository.fetchNotesFromLocal()
    }
}