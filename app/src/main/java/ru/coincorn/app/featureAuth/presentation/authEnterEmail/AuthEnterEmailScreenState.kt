package ru.coincorn.app.featureAuth.presentation.authEnterEmail

import androidx.annotation.StringRes

data class AuthEnterEmailScreenState(
    val email: String = "",
    val isEmailError: Boolean = false,
    @StringRes val emailError: Int? = null,
    val isLoading: Boolean = false
)
