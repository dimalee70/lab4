package kz.lab4.presentation.di

import kz.lab4.domain.usecase.AddTaskUseCase
import kz.lab4.domain.usecase.GetAllTasksUseCase
import kz.lab4.domain.usecase.GetTaskUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { AddTaskUseCase(taskRepository = get()) }
    factory { GetAllTasksUseCase(taskRepository = get()) }
    factory { GetTaskUseCase(taskRepository = get()) }
}