package kz.lab4.presentation.ui.ext

import androidx.lifecycle.MutableLiveData
import kz.lab4.presentation.ui.base.ResultState

fun <T> MutableLiveData<ResultState<T>>.setSuccess(data: T) =
    postValue(ResultState.Success(data))

fun <T> MutableLiveData<ResultState<T>>.setLoading() =
    postValue(ResultState.Loading(null))

fun <T> MutableLiveData<ResultState<T>>.setError(e: Throwable, message: String? = null, code: Any? = null) =
    postValue(ResultState.Error(e, message, code))

fun <T> MutableLiveData<T>.call() = postValue(null)

fun <T> MutableLiveData<ResultState<T>>.setSuccessDeleting(position: Int) =
    postValue(ResultState.SuccessDeleting(position))

fun <T> MutableLiveData<ResultState<T>>.setSuccessPagination(data: T) =
    postValue(ResultState.SuccessPagination(data))

fun <T> MutableLiveData<ResultState<T>>.setNotFound() =
    postValue(ResultState.NotFound(null))

fun <T> MutableLiveData<ResultState<T>>.setEmpty() =
    postValue(ResultState.Empty(null))

fun <T> MutableLiveData<ResultState<T>>.setSuccess() =
    postValue(ResultState.SuccessVoid(null))
