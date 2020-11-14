package kz.lab4.domain.usecase

import kz.lab4.domain.base.BaseUseCase
import kz.lab4.domain.base.Either
import kz.lab4.domain.base.Failure
import kz.lab4.domain.repository.TaskRepository
import kz.lab4.entity.todo.TaskRoom

class AddTaskUseCase(
    private val taskRepository: TaskRepository
) : BaseUseCase<Unit, TaskRoom>() {

    override suspend fun run(params: TaskRoom): Either<Failure, Unit> =
        try {
            Either.Right(taskRepository.addTask(params))
        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(Failure.FeatureFailure())
        }
}