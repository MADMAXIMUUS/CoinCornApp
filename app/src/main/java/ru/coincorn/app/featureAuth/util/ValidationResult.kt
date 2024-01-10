package ru.coincorn.app.featureAuth.util

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: Int? = null
)