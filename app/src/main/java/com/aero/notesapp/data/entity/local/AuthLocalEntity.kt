package com.aero.notesapp.data.entity.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aero.notesapp.domain.model.UserModel

@Entity(tableName = "Users")
data class UserLocalEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo(name = "username")
    val userName: String,
    @ColumnInfo
    val password: String,
)

fun UserLocalEntity.toModel(): UserModel {
    val user: UserModel = UserModel(id = this.id, name = this.name, userName = this.userName, password = this.password)
   return user
}