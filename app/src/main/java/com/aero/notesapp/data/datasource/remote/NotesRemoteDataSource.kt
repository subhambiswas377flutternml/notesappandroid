package com.aero.notesapp.data.datasource.remote

import com.aero.notesapp.data.entity.remote.NotesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NotesRemoteDataSource {
    @GET("/app/notes/getByUser/{userId}")
    suspend fun getNotesByUser(@Path("userId") userId: Int): NotesResponseDto
}