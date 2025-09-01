package com.aero.notesapp.domain.usecase.auth

import com.aero.notesapp.core.request.SignupRequest
import com.aero.notesapp.domain.model.UserModel
import com.aero.notesapp.domain.repository.AuthRepository

class SignupUsecase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(signupRequest: SignupRequest): UserModel{
        return authRepository.signup(signupRequest)
    }
}