package com.lifestrim.rpgdiary.data.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity (tableName = "task_table")
data class EntityTask(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "task_title")
    var taskTitle: String,

    @ColumnInfo(name = "task_description")
    var taskDescription: String,

    @ColumnInfo(name = "task_created_time")
    var taskCreatedTime: Date
)