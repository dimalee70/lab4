package kz.lab4.domain.repository

import kz.lab4.entity.todo.TaskRoom

interface TaskRepository {

    suspend fun addTask(taskRoom: TaskRoom)

    suspend fun getTask(id: Int): TaskRoom

    suspend fun getTasks(): List<TaskRoom>
}