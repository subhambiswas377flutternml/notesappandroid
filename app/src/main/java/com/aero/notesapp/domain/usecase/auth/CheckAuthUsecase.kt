package com.aero.notesapp.domain.usecase.auth

import com.aero.notesapp.domain.model.UserModel
import com.aero.notesapp.domain.repository.AuthRepository

class CheckAuthUsecase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): UserModel?{
        return authRepository.checkAuth()
    }
}