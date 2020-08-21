package com.lifestrim.rpgdiary.data.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lifestrim.rpgdiary.data.RPGDatabase
import com.lifestrim.rpgdiary.data.task.RepositoryTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel (application: Application) : AndroidViewModel(application) {
    private val repositoryTask: RepositoryTask
    val allTasks: LiveData<List<EntityTask>>
    init {
        val taskDao = RPGDatabase.getInstance(
            application
        ).taskDao()
        repositoryTask = RepositoryTask(taskDao)
        allTasks = repositoryTask.getAllTasks()
    }

    fun insertTask(task: EntityTask) = viewModelScope.launch(Dispatchers.IO)  {
        repositoryTask.insertTask(task)
    }

    fun deleteTask(task: EntityTask) = viewModelScope.launch(Dispatchers.IO) {
        repositoryTask.deleteTaskById(task)
    }

    fun updateTask(task: EntityTask) = viewModelScope.launch(Dispatchers.IO) {
        repositoryTask.updateTask(task)
    }
}