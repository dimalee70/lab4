package kz.lab4.presentation.ui.item

import androidx.lifecycle.viewModelScope
import kz.lab4.domain.base.Failure
import kz.lab4.domain.usecase.GetTaskUseCase
import kz.lab4.entity.todo.TaskUi
import kz.lab4.presentation.ui.base.BaseViewModel
import kz.lab4.presentation.ui.ext.setError
import kz.lab4.presentation.ui.ext.setLoading
import kz.lab4.presentation.ui.ext.setSuccess
import kz.lab4.presentation.ui.home.ItemArgs
import timber.log.Timber

class ItemViewModel(
    private val itemArgs: ItemArgs,
    private val getTaskUseCase: GetTaskUseCase
) : BaseViewModel<TaskUi>() {

    override fun loadData() {
        Timber.i("loadData")
        _pageResponse.setLoading()
        getTaskUseCase(viewModelScope, itemArgs.taskId) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleFailure(f: Failure) {
        Timber.i("handleFailure, f = $f")
        _pageResponse.setError(f.exception)
    }

    private fun handleSuccess(response: TaskUi) {
        Timber.i("handleSuccess, response = $response")
        _pageResponse.setSuccess(response)
    }
}