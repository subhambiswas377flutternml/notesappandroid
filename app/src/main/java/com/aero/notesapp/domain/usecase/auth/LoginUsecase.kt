package com.aero.notesapp.domain.usecase.auth

import com.aero.notesapp.core.request.auth.LoginRequest
import com.aero.notesapp.domain.model.UserModel
import com.aero.notesapp.domain.repository.AuthRepository

class LoginUsecase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(loginRequest: LoginRequest): UserModel{
        return authRepository.login(loginRequest)
    }
}