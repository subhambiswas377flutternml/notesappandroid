package com.aero.notesapp.data.repositoryimpl

import com.aero.notesapp.core.request.LoginRequest
import com.aero.notesapp.data.datasource.remote.AuthRemoteDataSource
import com.aero.notesapp.data.entity.toModel
import com.aero.notesapp.domain.model.AuthModel
import com.aero.notesapp.domain.repository.AuthRepository

class AuthRepositoryImpl(private val authRemoteDataSource: AuthRemoteDataSource): AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): AuthModel {
        val authResponse = authRemoteDataSource.login(loginRequest)
        return authResponse.toModel()
    }
}