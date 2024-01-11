package ru.coincorn.app.featureAuth.presentation.signIn

import androidx.annotation.StringRes

data class SignInScreenState(
    val email: String = "",
    val isEmailError: Boolean = false,
    @StringRes val emailError: Int? = null,
    val password: String = "",
    val isPasswordError: Boolean = false,
    @StringRes val passwordError: Int? = null,
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false
)
