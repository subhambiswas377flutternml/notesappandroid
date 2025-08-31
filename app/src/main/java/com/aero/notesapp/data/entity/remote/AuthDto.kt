package com.aero.notesapp.data.entity.remote

import com.aero.notesapp.data.entity.local.UserLocalEntity

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

fun AuthDto.toLocalEntity(): UserLocalEntity{
    val user: UserLocalEntity = UserLocalEntity(id = this.user.id, name = this.user.name, userName = this.user.userName, password = this.user.password)
    return user
}
