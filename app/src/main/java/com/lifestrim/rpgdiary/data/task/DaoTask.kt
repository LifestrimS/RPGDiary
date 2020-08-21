package com.lifestrim.rpgdiary.data.task

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DaoTask {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: EntityTask)

    @Update
    suspend fun updateTask(task: EntityTask)

    @Delete
    suspend fun deleteTask(task: EntityTask)

    @Query("SELECT * FROM task_table")
    fun getAllTask():LiveData<List<EntityTask>>
}