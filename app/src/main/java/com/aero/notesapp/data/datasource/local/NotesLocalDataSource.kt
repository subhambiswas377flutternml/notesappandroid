package com.aero.notesapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.aero.notesapp.data.entity.local.NotesLocalEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class NotesLocalDataSource {
    @Upsert
    abstract suspend fun upsert(notes:List<NotesLocalEntity>)

    @Query("Select * from Notes")
    abstract fun getAll(): Flow<List<NotesLocalEntity>>

    @Query("Delete from Notes where id=:noteId")
    abstract suspend fun deleteByNoteId(noteId:Int)

    @Query("Delete from Notes")
    abstract suspend fun clearNotesTable()
}