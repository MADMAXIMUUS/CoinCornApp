package ru.coincorn.app.featureAuth.presentation.signUp

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
import ru.coincorn.app.di.MainNavigation
import ru.coincorn.app.di.NestedNavigation
import ru.coincorn.app.featureAuth.data.response.AuthStep
import ru.coincorn.app.featureAuth.domain.repository.AuthRepository
import ru.coincorn.app.featureAuth.util.ValidateEmail
import ru.coincorn.app.featureAuth.util.ValidatePassword
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @NestedNavigation private val authNavigator: AppNavigator,
    @MainNavigation private val appNavigator: AppNavigator,
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpScreenState())
    val uiState: StateFlow<SignUpScreenState> = _uiState.asStateFlow()

    fun back() {
        viewModelScope.launch {
            authNavigator.back()
        }
    }

    fun updateName(name: String) {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    name = name,
                    isNameError = name.isEmpty(),
                    nameError = R.string.name_empty
                )
            }
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

    fun updatePassword(password: String) {
        val validResult = ValidatePassword().invoke(password)
        _uiState.update { currentState ->
            currentState.copy(
                password = password,
                isPasswordError = !validResult.successful,
                passwordError = validResult.errorMessage
            )
        }
    }

    fun updatePasswordVisibility() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isPasswordVisible = !currentState.isPasswordVisible
                )
            }
        }
    }

    fun signUp() {
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    isLoading = true
                )
            }
        }
        _uiState.update { currentState ->
            currentState.copy(
                isNameError = uiState.value.name.isEmpty(),
                nameError = R.string.name_empty,
                isEmailError = uiState.value.email.isEmpty(),
                emailError = R.string.email_is_empty,
                isPasswordError = uiState.value.password.isEmpty(),
                passwordError = R.string.password_empty
            )
        }
        val formIsValid = !uiState.value.isNameError
                && !uiState.value.isEmailError
                && !uiState.value.isPasswordError

        if (formIsValid) {
            viewModelScope.launch {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = true
                    )
                }
                authRepository.signUp(
                    uiState.value.name,
                    uiState.value.email,
                    uiState.value.password
                ).collectLatest {
                    if (it) {
                        appNavigator.newRootScreen(
                            Destination.RegistrationFlow,
                            AuthStep.CONFIRM.toString()
                        )
                    }
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