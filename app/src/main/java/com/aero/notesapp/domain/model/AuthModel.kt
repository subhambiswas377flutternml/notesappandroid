package com.aero.notesapp.domain.model

data class AuthModel(
    val status:Int,
    val message: String,
    val user: UserModel,
)

data class UserModel(
    val id:Int,
    val name: String,
    val userName: String,
    val password: String,
)