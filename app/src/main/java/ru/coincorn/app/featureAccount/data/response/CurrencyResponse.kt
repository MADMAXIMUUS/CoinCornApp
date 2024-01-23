package ru.coincorn.app.featureAccount.data.response

data class CurrencyResponse(
    val id: Long,
    val code: String,
    val name: String,
    val symbol: String,
    val divider: Int,
    val icon: String,
    val numericCode: Int
)