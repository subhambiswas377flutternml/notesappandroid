package com.aero.notesapp.data.repositoryimpl

import com.aero.notesapp.core.request.UpdateNoteRequest
import com.aero.notesapp.data.datasource.local.NotesLocalDataSource
import com.aero.notesapp.data.datasource.remote.NotesRemoteDataSource
import com.aero.notesapp.data.entity.local.toModel
import com.aero.notesapp.data.entity.remote.toLocalEntity
import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.domain.repository.NotesRepository

class NotesRepositoryImpl(private val notesRemoteDataSource: NotesRemoteDataSource,
    private val notesLocalDataSource: NotesLocalDataSource): NotesRepository {

    override suspend fun getNotesByUser(userId: Int): List<NotesModel>{
        val notesResponse = notesRemoteDataSource.getNotesByUser(userId = userId)
        if(notesResponse.status==200){
            notesLocalDataSource.upsert(notesResponse.toLocalEntity())
            return notesResponse.toLocalEntity().map { e-> e.toModel() }.toList()
        }else{
            throw Exception()
        }
    }

    override suspend fun updateNote(noteId: Int, updateNoteRequest: UpdateNoteRequest): List<NotesModel>{
        val updateNotesResponse = notesRemoteDataSource.updateNote(noteId = noteId, updateNoteRequest = updateNoteRequest)
        if(updateNotesResponse.status==200){
            notesLocalDataSource.upsert(updateNotesResponse.toLocalEntity())
            return updateNotesResponse.toLocalEntity().map { element-> element.toModel()  }.toList()
        }else{
            throw Exception()
        }
    }
}