package ru.coincorn.app.featureAuth.data.request

data class CodeRequestModel(
    val code: String,
    val os: String = "android",
    val email: String = "",
)