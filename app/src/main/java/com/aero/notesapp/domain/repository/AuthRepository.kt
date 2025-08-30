package com.aero.notesapp.domain.repository

import com.aero.notesapp.core.request.LoginRequest
import com.aero.notesapp.domain.model.AuthModel

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): AuthModel
}