package kz.lab4.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.lab4.entity.todo.TaskRoom

@Dao
abstract class TaskDao {

    @Query("SELECT * FROM TASKS")
    abstract suspend fun getTasks(): List<TaskRoom>

    @Query("DELETE FROM TASKS")
    abstract suspend fun deleteTasks()

    @Query("SELECT * FROM TASKS WHERE ID = :id")
    abstract suspend fun getTask(id: Int): TaskRoom

    @Insert
    abstract suspend fun addAll(tasks: List<TaskRoom>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(data: TaskRoom)

    @Query("DELETE FROM TASKS WHERE ID = :id")
    abstract suspend fun deleteFromFavorite(id: Long)
}