package com.studora.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.studora.app.data.dao.TaskDao
import com.studora.app.data.entities.TaskEntity

@Database(
    entities = [TaskEntity::class],
    version = 1
)

abstract class StudoraDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}