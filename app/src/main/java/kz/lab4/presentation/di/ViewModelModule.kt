package kz.lab4.presentation.di

import kz.lab4.presentation.ui.add.AddViewModel
import kz.lab4.presentation.ui.home.HomeViewModel
import kz.lab4.presentation.ui.home.ItemArgs
import kz.lab4.presentation.ui.item.ItemViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { AddViewModel(addTaskUseCase = get()) }
    viewModel { HomeViewModel(getAllTasksUseCase = get()) }
    viewModel { (itemArgs: ItemArgs) ->
        ItemViewModel(
            itemArgs = itemArgs,
            getTaskUseCase = get()
        )
    }
}