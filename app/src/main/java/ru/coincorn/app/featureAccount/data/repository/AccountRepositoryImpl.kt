package ru.coincorn.app.featureAccount.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.coincorn.app.core.error.ErrorHandler
import ru.coincorn.app.featureAccount.data.dataSource.AccountRemoteDataSource
import ru.coincorn.app.featureAccount.data.request.CreateAccountRequestModel
import ru.coincorn.app.featureAccount.data.response.AccountTypeResponse
import ru.coincorn.app.featureAccount.data.response.CurrencyResponse
import ru.coincorn.app.featureAccount.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val errorHandler: ErrorHandler
) : AccountRepository {
    override fun createAccount(
        amount: Double,
        type: Long,
        currency: Long
    ): Flow<Boolean> {
        val createAccountRequestModel = CreateAccountRequestModel(amount, type, currency)
        return accountRemoteDataSource
            .createAccount(createAccountRequestModel)
            .catch { errorHandler.proceed(it) }
            .flowOn(Dispatchers.IO)
            .map { true }
    }

    override fun getAccountTypes(): Flow<List<AccountTypeResponse>> {
        return accountRemoteDataSource.getAccountTypes()
            .catch { errorHandler.proceed(it) }
            .flowOn(Dispatchers.IO)
    }

    override fun getCurrencyList(): Flow<List<CurrencyResponse>> {
        return accountRemoteDataSource.getCurrencyList()
            .catch { errorHandler.proceed(it) }
            .flowOn(Dispatchers.IO)
    }
}