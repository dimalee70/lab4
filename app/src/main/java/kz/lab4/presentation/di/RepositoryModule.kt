package kz.lab4.presentation.di

import kz.lab4.data.repository.DefaultTaskRepository
import kz.lab4.domain.repository.TaskRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<TaskRepository> {
        DefaultTaskRepository(
            taskDao = get()
        )
    }
}