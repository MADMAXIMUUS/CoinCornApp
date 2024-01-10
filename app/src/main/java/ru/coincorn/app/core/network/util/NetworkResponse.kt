package ru.coincorn.app.core.network.util

import okhttp3.Headers

sealed class NetworkResponse<out T : Any> {

    data class Success<T : Any>(
        val body: T?,
        val headers: Headers
    ) : NetworkResponse<T>()

    data class Error(val error: Throwable) : NetworkResponse<Nothing>()
}

suspend fun <T : Any> NetworkResponse<T>.onSuccess(
    executable: suspend (T?, Headers) -> Unit
): NetworkResponse<T> = apply {
    if (this is NetworkResponse.Success<T>) {
        executable(body, headers)
    }
}

suspend fun <T : Any> NetworkResponse<T>.onError(
    executable: suspend (e: Throwable) -> Unit
): NetworkResponse<T> = apply {
    if (this is NetworkResponse.Error) {
        executable(error)
    }
}