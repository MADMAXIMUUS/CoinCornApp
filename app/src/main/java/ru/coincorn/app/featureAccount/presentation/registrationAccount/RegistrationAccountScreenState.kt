package ru.coincorn.app.featureAccount.presentation.registrationAccount

import ru.coincorn.app.featureAccount.data.response.AccountTypeResponse
import ru.coincorn.app.featureAccount.data.response.CurrencyResponse

data class RegistrationAccountScreenState(
    val amount: String = "",
    val currencyListResponse: List<CurrencyResponse> = emptyList(),
    val accountTypesResponse: List<AccountTypeResponse> = emptyList(),
    val selectedCurrency: String = "",
    val selectedCurrencyIndex: Int = 0,
    val selectedAccountType: String = "",
    val selectedAccountTypeIndex: Int = 0,
    val isLoading: Boolean = false
)
