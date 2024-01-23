package ru.coincorn.app.featureAccount.data.dataSource

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.coincorn.app.core.network.util.onError
import ru.coincorn.app.core.network.util.onSuccess
import ru.coincorn.app.featureAccount.data.api.AccountApi
import ru.coincorn.app.featureAccount.data.request.CreateAccountRequestModel
import ru.coincorn.app.featureAccount.data.response.AccountTypeResponse
import ru.coincorn.app.featureAccount.data.response.CurrencyResponse
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(
    private val api: AccountApi
) {

    fun createAccount(createAccountRequestModel: CreateAccountRequestModel): Flow<Boolean> = flow {
        api.createAccount(createAccountRequestModel)
            .onSuccess { _, _ ->
                emit(true)
            }
            .onError { throw it }
    }

    fun getAccountTypes(): Flow<List<AccountTypeResponse>> = flow {
        api.getAccountTypes()
            .onSuccess { response, _ ->
                response?.let { emit(it) }
            }
            .onError { throw it }
    }

    fun getCurrencyList(): Flow<List<CurrencyResponse>> = flow {
        api.getCurrenciesList()
            .onSuccess { response, _ ->
                response?.let { emit(it) }
            }
            .onError { throw it }
    }

}