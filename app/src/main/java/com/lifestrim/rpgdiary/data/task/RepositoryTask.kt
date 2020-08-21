package com.lifestrim.rpgdiary.data.task

import androidx.lifecycle.LiveData

class RepositoryTask (private val taskDaoTask: DaoTask) {

    fun getAllTasks(): LiveData<List<EntityTask>> {
        val allTasks: LiveData<List<EntityTask>> = taskDaoTask.getAllTask()
        return allTasks
    }

    suspend fun insertTask(task: EntityTask) {
        taskDaoTask.insertTask(task)
    }

    suspend fun deleteTaskById(task: EntityTask) {
        taskDaoTask.deleteTask(task)
    }

    suspend fun updateTask(task: EntityTask) {
        taskDaoTask.updateTask(task)
    }

}