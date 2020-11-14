package kz.lab4.data.repository

import kz.lab4.data.dao.TaskDao
import kz.lab4.domain.repository.TaskRepository
import kz.lab4.entity.todo.TaskRoom

class DefaultTaskRepository(
    private val taskDao: TaskDao
) : TaskRepository {

    override suspend fun addTask(taskRoom: TaskRoom) {
        taskDao.insert(taskRoom)
    }

    override suspend fun getTask(id: Int) =
        taskDao.getTask(id)

    override suspend fun getTasks(): List<TaskRoom> =
        taskDao.getTasks()
}