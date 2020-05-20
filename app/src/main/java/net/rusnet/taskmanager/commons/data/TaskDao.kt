package net.rusnet.taskmanager.commons.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import net.rusnet.taskmanager.commons.domain.model.Task
import net.rusnet.taskmanager.commons.domain.model.TaskType

@Dao
interface TaskDao {

    @Query("SELECT * FROM task_table WHERE id = :id")
    suspend fun getTask(id: Long): Task

    @Update
    suspend fun updateTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<Task>)

    @Delete
    suspend fun deleteTask(task: Task)

    @Delete
    suspend fun deleteTasks(tasks: List<Task>)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

    @Query("SELECT * from task_table")
    suspend fun getAllTasks(): List<Task>

    @Query(
        "SELECT * " +
                "FROM task_table " +
                "WHERE (task_type = :taskType) " +
                "AND (:checkDates = 0 OR end_date IS NOT NULL = :hasDates) " +
                "ORDER BY end_date IS NULL, start_date, end_date, id ASC"
    )
    suspend fun getTasks(
        taskType: TaskType?,
        checkDates: Boolean,
        hasDates: Boolean?
    ): List<Task>

    @Query(
        "SELECT COUNT(*) " +
                "FROM task_table " +
                "WHERE (task_type = :taskType) " +
                "AND (:checkDates = 0 OR end_date IS NOT NULL = :hasDates)"
    )
    suspend fun getTasksCount(
        taskType: TaskType?,
        checkDates: Boolean,
        hasDates: Boolean?
    ): Long

    @Query("UPDATE task_table SET task_type = :newTaskType WHERE id = :taskId")
    suspend fun updateTaskType(taskId: Long, newTaskType: TaskType)

}