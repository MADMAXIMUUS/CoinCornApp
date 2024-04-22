package ru.coincorn.app.featureAuth.presentation.authEnterEmail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.coincorn.app.R
import ru.coincorn.app.core.navigation.AppNavigator
import ru.coincorn.app.core.navigation.Destination
import ru.coincorn.app.di.NestedNavigation
import ru.coincorn.app.featureAuth.domain.repository.AuthRepository
import ru.coincorn.app.featureAuth.util.ValidateEmail
import javax.inject.Inject

@HiltViewModel
class AuthEnterEmailViewModel @Inject constructor(
    @NestedNavigation private val authNavigator: AppNavigator,
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthEnterEmailScreenState())
    val uiState: StateFlow<AuthEnterEmailScreenState> = _uiState.asStateFlow()

    fun back() {
        viewModelScope.launch {
            authNavigator.back()
        }
    }

    fun updateEmail(email: String) {
        val validResult = ValidateEmail().invoke(email)
        _uiState.update { currentState ->
            currentState.copy(
                email = email,
                isEmailError = !validResult.successful,
                emailError = validResult.errorMessage
            )
        }
    }

    fun sendCode() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }
        }
        _uiState.update { currentState ->
            currentState.copy(
                isEmailError = uiState.value.email.isEmpty(),
                emailError = R.string.email_is_empty,
            )
        }

        if (!uiState.value.isEmailError) {
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = true
                    )
                }
                authRepository
                    .auth(uiState.value.email)
                    .collectLatest {
                        authNavigator.navigateTo(Destination.AuthEnterCode, uiState.value.email)
                    }
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false
                    )
                }
            }
        }
    }
}