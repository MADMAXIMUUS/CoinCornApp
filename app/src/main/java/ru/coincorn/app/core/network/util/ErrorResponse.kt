package ru.coincorn.app.core.network.util

data class ErrorResponse(
    val title: String? = null,
    val localizedMessage: String? = null,
    val status: Int? = null,
    val code: Int? = null,
)