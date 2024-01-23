package ru.coincorn.app.featureAccount.data.request

data class CreateAccountRequestModel(
    val amount: Double,
    val type: Long,
    val currency: Long
)