package kz.lab4.domain.usecase

import kz.lab4.domain.base.BaseUseCase
import kz.lab4.domain.base.Either
import kz.lab4.domain.base.Failure
import kz.lab4.domain.repository.TaskRepository
import kz.lab4.entity.todo.TaskRoom
import kz.lab4.entity.todo.TaskUi
import timber.log.Timber

class GetAllTasksUseCase(
    private val taskRepository: TaskRepository
) : BaseUseCase<List<TaskUi>, Boolean>() {
    override suspend fun run(params: Boolean): Either<Failure, List<TaskUi>> =
        try {
            Either.Right(mapTasks(input = taskRepository.getTasks()))
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(Failure.FeatureFailure())
        }

    private fun mapTasks(input: List<TaskRoom>): List<TaskUi> {
        Timber.i("mapMovieResponse")
        return input.map {
            TaskUi(
                id = it.id!!,
                title = it.title,
                description = it.description,
                status = it.status,
                category = it.category,
                duration = it.duration
            )
        }
    }
}