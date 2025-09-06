package com.aero.notesapp.domain.repository

import com.aero.notesapp.core.request.auth.LoginRequest
import com.aero.notesapp.core.request.auth.SignupRequest
import com.aero.notesapp.domain.model.UserModel

interface AuthRepository {
    suspend fun login(loginRequest: LoginRequest): UserModel
    suspend fun checkAuth():UserModel?
    suspend fun signup(signupRequest: SignupRequest): UserModel
}