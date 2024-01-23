package ru.coincorn.app.featureAccount.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.coincorn.app.core.network.util.NetworkResponse
import ru.coincorn.app.featureAccount.data.request.CreateAccountRequestModel
import ru.coincorn.app.featureAccount.data.response.AccountTypeResponse
import ru.coincorn.app.featureAccount.data.response.CurrencyResponse

interface AccountApi {

    @GET("/api/v1/account/types")
    suspend fun getAccountTypes(): NetworkResponse<List<AccountTypeResponse>>

    @POST("/api/v1/account/create")
    suspend fun createAccount(
        @Body body: CreateAccountRequestModel
    ): NetworkResponse<Unit>

    @GET("/api/v1/currency/list")
    suspend fun getCurrenciesList(): NetworkResponse<List<CurrencyResponse>>
}