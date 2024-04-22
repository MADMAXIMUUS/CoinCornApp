package ru.coincorn.app.core.navigation

sealed class Destination(protected val route: String, vararg params: String) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    open operator fun invoke(): String = route

    open fun generateFullStrung(vararg args: String): String = route

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        override operator fun invoke(): String = route
    }

    data object Root : NoArgumentsDestination("root")

    data object Intro : NoArgumentsDestination("intro")

    data object AuthFlow : NoArgumentsDestination("auth_flow")

    data object Welcome : NoArgumentsDestination("welcome")

    data object AuthEnterEmail : NoArgumentsDestination("auth_enter_email")

    data object AuthEnterCode : Destination("auth_enter_code", "email_param"){
        override fun generateFullStrung(vararg args: String): String = route.appendParams(
            "email_param" to args[0]
        )
    }

    data object RegistrationNameFlow : NoArgumentsDestination("registration_name_flow")

    data object RegistrationName : NoArgumentsDestination("registration_name")

    data object RegistrationAccountFlow : NoArgumentsDestination("registration_account_flow")

    data object RegistrationAccountIntro : NoArgumentsDestination("registration_account_intro")

    data object RegistrationAccount : NoArgumentsDestination("registration_account")
    data object RegistrationAccountFinish : NoArgumentsDestination("registration_account_finish")

    data object MainFlow : NoArgumentsDestination("main_flow")

    /* Errors */
    data object CommonError : Destination("common_error", "error_model") {

        override fun generateFullStrung(vararg args: String): String = route.appendParams(
            "error_model" to args[0]
        )
    }

    data object ConnectionError : NoArgumentsDestination("connection_error")

    data object AuthError : NoArgumentsDestination("auth_error")
    /* Errors */
}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}