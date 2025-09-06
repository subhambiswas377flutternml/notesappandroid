package com.aero.notesapp.data.repositoryimpl

import com.aero.notesapp.core.request.auth.LoginRequest
import com.aero.notesapp.core.request.auth.SignupRequest
import com.aero.notesapp.data.datasource.local.AuthLocalDataSource
import com.aero.notesapp.data.datasource.remote.AuthRemoteDataSource
import com.aero.notesapp.data.entity.local.toModel
import com.aero.notesapp.data.entity.remote.toLocalEntity
import com.aero.notesapp.domain.model.UserModel
import com.aero.notesapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.firstOrNull

class AuthRepositoryImpl(private val authRemoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource): AuthRepository {

    override suspend fun login(loginRequest: LoginRequest): UserModel {
        val authResponse = authRemoteDataSource.login(loginRequest)
        if(authResponse.status==200){
            authLocalDataSource.insert(authResponse.toLocalEntity())
            return authResponse.toLocalEntity().toModel()
        }else{
           throw Exception()
        }
    }

    override suspend fun checkAuth():UserModel? {
        val user = authLocalDataSource.read().firstOrNull()?.firstOrNull()
        return if(user!=null){
            user.toModel()
        }else{
            null
        }
    }

    override suspend fun signup(signupRequest: SignupRequest): UserModel{
        val authResponse = authRemoteDataSource.singup(signupRequest)
        if(authResponse.status==200){
            authLocalDataSource.insert(authResponse.toLocalEntity())
            return authResponse.toLocalEntity().toModel()
        }else{
            throw Exception()
        }
    }
}