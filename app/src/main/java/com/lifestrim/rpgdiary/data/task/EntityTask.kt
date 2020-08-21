package com.lifestrim.rpgdiary.data.task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "task_table")
data class EntityTask(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var taskTitle: String,
    var taskDescription: String
)