package com.aero.notesapp.domain.usecase

import com.aero.notesapp.core.request.LoginRequest
import com.aero.notesapp.domain.model.AuthModel
import com.aero.notesapp.domain.repository.AuthRepository

class LoginUsecase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(loginRequest: LoginRequest): AuthModel{
        return authRepository.login(loginRequest)
    }
}