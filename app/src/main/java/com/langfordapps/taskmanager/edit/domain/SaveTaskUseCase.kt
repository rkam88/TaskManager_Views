package com.langfordapps.taskmanager.edit.domain

import com.langfordapps.taskmanager.commons.data.TasksDataSource
import com.langfordapps.taskmanager.commons.domain.model.Task
import dagger.Reusable
import javax.inject.Inject

@Reusable
class SaveTaskUseCase @Inject constructor(
    private val tasksDataSource: TasksDataSource
) {

    suspend fun execute(taskToSave: Task) {
        tasksDataSource.saveTask(taskToSave)
    }

}