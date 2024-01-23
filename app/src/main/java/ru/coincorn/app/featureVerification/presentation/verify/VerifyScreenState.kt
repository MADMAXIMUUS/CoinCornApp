package ru.coincorn.app.featureVerification.presentation.verify

data class VerifyScreenState(
    val code: List<String> = listOf("", "", "", "", "", ""),
    val timer: String = "",
    val resendEnabled: Boolean = false,
    val email: String = "",
    val verifyLoading: Boolean = false,
    val resendLoading: Boolean = false
)
