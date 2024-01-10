package ru.coincorn.app.featureAuth.presentation.signUp

import androidx.annotation.StringRes

data class SignUpScreenState(
    val name: String = "",
    val isNameError: Boolean = false,
    @StringRes val nameError: Int? = null,
    val email: String = "",
    val isEmailError: Boolean = false,
    @StringRes val emailError: Int? = null,
    val password: String = "",
    val isPasswordError: Boolean = false,
    @StringRes val passwordError: Int? = null,
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false
)
