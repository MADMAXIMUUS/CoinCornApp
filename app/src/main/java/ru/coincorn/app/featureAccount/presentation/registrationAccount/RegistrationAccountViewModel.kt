package ru.coincorn.app.featureAccount.presentation.registrationAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.di.NestedNavigation
import ru.coincorn.app.featureAccount.domain.repository.AccountRepository
import javax.inject.Inject

@HiltViewModel
class RegistrationAccountViewModel @Inject constructor(
    @NestedNavigation private val accountNavigator: AppNavigator,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegistrationAccountScreenState())
    val uiState: StateFlow<RegistrationAccountScreenState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            accountRepository.getCurrencyList().collectLatest {
                _uiState.update { currentState ->
                    currentState.copy(
                        currencyListResponse = it,
                    )
                }
            }
            accountRepository.getAccountTypes().collectLatest {
                _uiState.update { currentState ->
                    currentState.copy(
                        accountTypesResponse = it,
                    )
                }
            }
        }
    }

    fun back() {
        viewModelScope.launch {
            accountNavigator.back()
        }
    }

    fun updateAmount(newValue: String) {
        viewModelScope.launch {
            val input = newValue.replace(",", ".").split(".").toMutableList()
            //var output = if (newValue.isNotEmpty() && newValue[0] == '.') "0" else ""
            var output = ""
            input.forEachIndexed { index, s ->
                output += if (index == 1)
                    ".$s"
                else
                    s
            }
            _uiState.update { currentState ->
                currentState.copy(
                    amount = output
                )
            }
        }
    }

    fun updateSelectedCurrency(newValue: String, index: Int) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    selectedCurrency = newValue,
                    selectedCurrencyIndex = index
                )
            }
        }
    }

    fun updateSelectedType(newValue: String, index: Int) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    selectedAccountType = newValue,
                    selectedAccountTypeIndex = index
                )
            }
        }
    }

    fun createAccount() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(isLoading = true)
            }
            val currency = uiState.value.currencyListResponse[uiState.value.selectedCurrencyIndex]
            val type = uiState.value.accountTypesResponse[uiState.value.selectedAccountTypeIndex]

            accountRepository
                .createAccount(
                    amount = uiState.value.amount.toDouble(),
                    currency = currency.id,
                    type = type.id
                )
                .collectLatest {
                    if (it) {
                        accountNavigator.newRootScreen(Destination.RegistrationAccountFinish)
                    }
                }
            _uiState.update { currentState ->
                currentState.copy(isLoading = false)
            }
        }
    }

}