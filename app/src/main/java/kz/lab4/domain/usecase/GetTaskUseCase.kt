package kz.lab4.domain.usecase

import kz.lab4.domain.base.BaseUseCase
import kz.lab4.domain.base.Either
import kz.lab4.domain.base.Failure
import kz.lab4.domain.repository.TaskRepository
import kz.lab4.entity.todo.TaskRoom
import kz.lab4.entity.todo.TaskUi
import timber.log.Timber

class GetTaskUseCase(
    private val taskRepository: TaskRepository
) : BaseUseCase<TaskUi, Int>() {

    override suspend fun run(params: Int): Either<Failure, TaskUi> =
        try {
            Either.Right(mapTask(taskRepository.getTask(params)))
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(Failure.FeatureFailure())
        }

    private fun mapTask(input: TaskRoom): TaskUi {
        Timber.i("mapTask")
        return TaskUi(
            id = input.id!!,
            title = input.title,
            description = input.description,
            status = input.status,
            category = input.category,
            duration = input.duration
        )
    }
}