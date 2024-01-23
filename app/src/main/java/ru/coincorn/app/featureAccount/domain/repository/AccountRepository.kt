package ru.coincorn.app.featureAccount.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.coincorn.app.featureAccount.data.response.AccountTypeResponse
import ru.coincorn.app.featureAccount.data.response.CurrencyResponse

interface AccountRepository {

    fun createAccount(
        amount: Double,
        type: Long,
        currency: Long
    ): Flow<Boolean>

    fun getAccountTypes(): Flow<List<AccountTypeResponse>>

    fun getCurrencyList(): Flow<List<CurrencyResponse>>
}