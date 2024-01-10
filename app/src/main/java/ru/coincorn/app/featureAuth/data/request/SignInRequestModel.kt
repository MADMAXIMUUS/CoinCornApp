package ru.coincorn.app.featureAuth.data.request

data class SignInRequestModel(
    val email: String = "",
    val password: String = ""
)