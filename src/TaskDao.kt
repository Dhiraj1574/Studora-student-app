package com.studora.app.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.studora.app.data.entities.TaskEntity

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTask(task: TaskEntity)

    @Query("SELECT * FROM tasks")
    suspend fun getAllTasks(): List<TaskEntity>

    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTask(taskId: Int)
}