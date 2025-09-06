package com.aero.notesapp.data.datasource.remote

import com.aero.notesapp.core.request.UpdateNoteRequest
import com.aero.notesapp.data.entity.remote.NotesResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface NotesRemoteDataSource {
    @GET("/app/notes/getByUser/{userId}")
    suspend fun getNotesByUser(@Path("userId") userId: Int): NotesResponseDto

    @PATCH("/app/notes/updateById/{noteId}")
    suspend fun updateNote(@Path("noteId") noteId: Int, @Body updateNoteRequest: UpdateNoteRequest): NotesResponseDto
}