package com.aero.notesapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aero.notesapp.data.entity.local.UserLocalEntity
import kotlinx.coroutines.flow.Flow

@Dao
abstract class AuthLocalDataSource {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(userLocalEntity: UserLocalEntity)

    @Query("select * from Users")
    abstract fun read(): Flow<List<UserLocalEntity>>

    @Update
    abstract suspend fun update(userLocalEntity: UserLocalEntity)

    @Delete
    abstract suspend fun delete(userLocalEntity: UserLocalEntity)
}