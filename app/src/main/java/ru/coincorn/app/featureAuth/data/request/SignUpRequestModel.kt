package ru.coincorn.app.featureAuth.data.request

data class SignUpRequestModel(
    val name: String = "",
    val email: String = "",
    val password: String = ""
)
