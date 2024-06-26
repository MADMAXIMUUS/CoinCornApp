package ru.coincorn.app.featureAuth.presentation.authEnterCode

data class AuthEnterCodeScreenState(
    val code: List<String> = listOf("", "", "", "", "", ""),
    val timer: String = "",
    val resendEnabled: Boolean = false,
    val email: String = "",
    val verifyLoading: Boolean = false,
    val resendLoading: Boolean = false
)
