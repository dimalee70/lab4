package kz.lab4.presentation.ui.home

import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import kz.lab4.domain.base.Failure
import kz.lab4.domain.usecase.GetAllTasksUseCase
import kz.lab4.entity.todo.TaskUi
import kz.lab4.presentation.ui.base.BaseViewModel
import kz.lab4.presentation.ui.base.Event
import kz.lab4.presentation.ui.ext.setError
import kz.lab4.presentation.ui.ext.setLoading
import kz.lab4.presentation.ui.ext.setSuccess
import timber.log.Timber

class HomeViewModel(
    private val getAllTasksUseCase: GetAllTasksUseCase
) : BaseViewModel<List<TaskUi>>() {

    private val _itemArgs: LiveEvent<Event<ItemArgs>> = LiveEvent()
    val itemArgs
        get() = _itemArgs

    override fun loadData() {
        Timber.i("loadData")
        _pageResponse.setLoading()
        getAllTasksUseCase(viewModelScope, params = false) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(response: List<TaskUi>) {
        Timber.i("handleSuccess")
        _pageResponse.setSuccess(response)
    }

    private fun handleFailure(f: Failure) {
        Timber.i("handleFailure, f = $f")
        _pageResponse.setError(f.exception)
    }

    fun onClickTask(taskItem: TaskItem) {
        Timber.i("onClickTask, taskItem = $taskItem")
        val itemArgs = ItemArgs(taskId = taskItem.taskUi.id!!)
        _itemArgs.postValue(Event(itemArgs))
    }
}