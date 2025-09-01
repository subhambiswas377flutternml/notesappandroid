package com.aero.notesapp.data.entity.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aero.notesapp.domain.model.NotesModel

@Entity(tableName = "Notes")
data class NotesLocalEntity(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    @ColumnInfo(name = "userid")
    val userId: Int,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val description: String,
)

fun NotesLocalEntity.toModel(): NotesModel{
    return NotesModel(id = this.id, userId = this.userId, title=this.title, description=this.description)
}