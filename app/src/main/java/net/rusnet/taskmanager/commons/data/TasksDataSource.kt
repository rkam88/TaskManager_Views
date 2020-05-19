package net.rusnet.taskmanager.commons.data

import dagger.Reusable
import net.rusnet.taskmanager.commons.domain.BaseFilter
import net.rusnet.taskmanager.commons.domain.model.Task
import javax.inject.Inject

@Reusable
class TasksDataSource @Inject constructor(private val taskDao: TaskDao) {

    suspend fun saveTask(taskToSave: Task) {
        taskDao.insertTask(taskToSave)
    }

    suspend fun getTasksCount(filter: BaseFilter): Long {
        return taskDao.getTasksCount(
            isInTrash = filter.isInTrash,
            checkCompletedStatus = filter.isCompleted != null,
            isCompleted = filter.isCompleted,
            checkTaskType = filter.taskType != null,
            taskType = filter.taskType,
            checkDates = filter.hasDates != null,
            hasDates = filter.hasDates
        )
    }

    suspend fun getTasks(filter: BaseFilter): List<Task> {
        return taskDao.getTasks(
            isInTrash = filter.isInTrash,
            checkCompletedStatus = filter.isCompleted != null,
            isCompleted = filter.isCompleted,
            checkTaskType = filter.taskType != null,
            taskType = filter.taskType,
            checkDates = filter.hasDates != null,
            hasDates = filter.hasDates
        )
    }

}