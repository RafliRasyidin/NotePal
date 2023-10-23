package com.rasyidin.notepal.domain

import kotlinx.coroutines.flow.MutableStateFlow

sealed class ResultState<A: Any> {
    class Loading<T: Any> : ResultState<T>()
    class Idle<T: Any> : ResultState<T>()
    class Empty<T: Any> : ResultState<T>()
    data class Error<T: Any>(val throwable: Throwable) : ResultState<T>()
    data class Success<T: Any>(val data: T?) : ResultState<T>()
}

inline fun <T: Any, U: Any> mapResult(
    resultState: ResultState<out T>,
    mapper: T?.() -> U?
): ResultState<U> {
    return when (resultState) {
        is ResultState.Empty -> ResultState.Empty()
        is ResultState.Error -> ResultState.Error(resultState.throwable)
        is ResultState.Idle -> ResultState.Idle()
        is ResultState.Loading -> ResultState.Loading()
        is ResultState.Success -> {
            val data = resultState.data
            val mappedData = mapper.invoke(data)
            ResultState.Success(mappedData)
        }
    }
}

fun <T: Any> ResultState<T>.onError(result: (Throwable) -> Unit) {
    if (this is ResultState.Error) {
        result(this.throwable)
    }
}

fun <T : Any> ResultState<T>.onSuccess(result: (T?) -> Unit) {
    if (this is ResultState.Success) {
        result.invoke(this.data)
    }
}

fun <T : Any> ResultState<T>.onLoading(result: () -> Unit) {
    if (this is ResultState.Loading) {
        result.invoke()
    }
}

fun <T : Any> ResultState<T>.onEmpty(result: () -> Unit) {
    if (this is ResultState.Empty) {
        result.invoke()
    }
}

fun <T : Any> idle(): MutableStateFlow<ResultState<T>> = run {
    MutableStateFlow(ResultState.Idle())
}
