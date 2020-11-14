package kz.lab4.presentation.ui.add

import androidx.lifecycle.viewModelScope
import com.hadilq.liveevent.LiveEvent
import kz.lab4.domain.base.Failure
import kz.lab4.domain.usecase.AddTaskUseCase
import kz.lab4.entity.todo.TaskRoom
import kz.lab4.entity.todo.TaskUi
import kz.lab4.presentation.ui.base.BaseViewModel
import kz.lab4.presentation.ui.base.Event
import kz.lab4.presentation.ui.ext.setError
import kz.lab4.presentation.ui.ext.setLoading
import kz.lab4.presentation.ui.ext.setSuccess
import timber.log.Timber

class AddViewModel(
    private val addTaskUseCase: AddTaskUseCase
) : BaseViewModel<Unit>() {

    private val _action: LiveEvent<Event<Actions>> = LiveEvent()
    val action
        get() = _action

    fun addTask(taskUi: TaskUi) {
        Timber.i("addTask, taskUi = $taskUi")
        _pageResponse.setLoading()
        addTaskUseCase(viewModelScope, transformToRoom(taskUi)) {
            it.either(::handleFailure, ::handleSuccess)
        }
    }

    private fun handleSuccess(response: Unit) {
        Timber.i("handleSuccess, response = $response")
        _pageResponse.setSuccess(response)
        _action.postValue(Event(Actions.ON_ADD_TASK_SUCCESS))
    }

    private fun handleFailure(f: Failure) {
        Timber.i("handleFailure, f = $f")
        _pageResponse.setError(f.exception)
        _action.postValue(Event(Actions.ON_ADD_TASK_ERROR))
    }

    private fun transformToRoom(input: TaskUi): TaskRoom =
        TaskRoom(
            id = input.id,
            title = input.title,
            description = input.description,
            status = input.status,
            category = input.category,
            duration = input.duration
        )

    enum class Actions {
        ON_ADD_TASK_SUCCESS,
        ON_ADD_TASK_ERROR
    }

}