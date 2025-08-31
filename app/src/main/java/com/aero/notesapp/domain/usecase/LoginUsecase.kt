package com.aero.notesapp.domain.usecase

import com.aero.notesapp.core.request.LoginRequest
import com.aero.notesapp.domain.model.UserModel
import com.aero.notesapp.domain.repository.AuthRepository

class LoginUsecase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(loginRequest: LoginRequest): UserModel{
        return authRepository.login(loginRequest)
    }
}