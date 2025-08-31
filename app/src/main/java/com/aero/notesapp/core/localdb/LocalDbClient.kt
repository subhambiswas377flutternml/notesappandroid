package com.aero.notesapp.core.localdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aero.notesapp.data.datasource.local.AuthLocalDataSource
import com.aero.notesapp.data.entity.local.UserLocalEntity

@Database(
    exportSchema = false,
    version = 1,
    entities = [
        UserLocalEntity::class
    ]
)
abstract class LocalDbClient: RoomDatabase() {
    abstract fun initAuthDb(): AuthLocalDataSource
}