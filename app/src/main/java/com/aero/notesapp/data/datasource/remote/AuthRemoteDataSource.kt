package com.aero.notesapp.data.datasource.remote

import com.aero.notesapp.core.request.auth.LoginRequest
import com.aero.notesapp.core.request.auth.SignupRequest
import com.aero.notesapp.data.entity.remote.AuthDto
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface AuthRemoteDataSource {
    @POST("/app/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest,
                      @HeaderMap header: Map<String, String> = mapOf("Accept" to "application/json")
    ): AuthDto

    @POST("/app/auth/signup")
    suspend fun singup(@Body signupRequest: SignupRequest,
                       @HeaderMap header: Map<String, String> = mapOf<String, String>("Accept" to "application/json")
    ): AuthDto
}