package com.aero.notesapp.data.entity

import com.aero.notesapp.domain.model.AuthModel
import com.aero.notesapp.domain.model.UserModel

data class AuthDto(
    val status:Int,
    val message: String,
    val user: UserDto,
)

data class UserDto(
    val id:Int,
    val name: String,
    val userName: String,
    val password: String,
)

fun AuthDto.toModel(): AuthModel{
    val user: UserModel = UserModel(id = this.user.id, name = this.user.name, userName = this.user.userName, password = this.user.password)
    return AuthModel(status = this.status, message=this.message, user = user)
}