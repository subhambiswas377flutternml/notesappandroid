package com.aero.notesapp.data.repositoryimpl

import com.aero.notesapp.core.request.note.AddNoteRequest
import com.aero.notesapp.core.request.note.UpdateNoteRequest
import com.aero.notesapp.data.datasource.local.NotesLocalDataSource
import com.aero.notesapp.data.datasource.remote.NotesRemoteDataSource
import com.aero.notesapp.data.entity.local.toModel
import com.aero.notesapp.data.entity.remote.toLocalEntity
import com.aero.notesapp.domain.model.NotesModel
import com.aero.notesapp.domain.repository.NotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList

class NotesRepositoryImpl(private val notesRemoteDataSource: NotesRemoteDataSource,
    private val notesLocalDataSource: NotesLocalDataSource): NotesRepository {

    override suspend fun getNotesByUser(userId: Int): List<NotesModel>{
        val notesResponse = notesRemoteDataSource.getNotesByUser(userId = userId)
        if(notesResponse.status==200){
            notesLocalDataSource.clearNotesTable()
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

    override suspend fun addNoteByUser(addNoteRequest: AddNoteRequest): List<NotesModel>{
        val addResponse = notesRemoteDataSource.addNoteByUser(addNoteRequest = addNoteRequest)
        if(addResponse.status==200){
            notesLocalDataSource.upsert(addResponse.toLocalEntity())
            return addResponse.toLocalEntity().map { element-> element.toModel() }.toList()
        }else{
            throw Exception()
        }
    }

    override suspend fun deleteByNoteId(noteId: Int): List<NotesModel>{
        val postDeleteResponse = notesRemoteDataSource.deleteByNoteId(noteId = noteId)
        if(postDeleteResponse.status==200){
            notesLocalDataSource.deleteByNoteId(noteId = noteId)
            return postDeleteResponse.toLocalEntity().map { element-> element.toModel() }.toList()
        }else{
            throw Exception()
        }
    }

    override suspend fun fetchNotesFromLocal(): List<NotesModel> {
        val localNotes = notesLocalDataSource.getAll()
        return localNotes.first().map { element-> element.toModel() }.toList()
    }
}