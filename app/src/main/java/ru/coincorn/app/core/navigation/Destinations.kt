package ru.coincorn.app.core.navigation

import ru.coincorn.app.core.error.model.CommonErrorModel
import ru.coincorn.app.featureAuth.data.response.AuthStep

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

    data object SignUp : NoArgumentsDestination("sign_up")

    data object Currency : NoArgumentsDestination("registration_currency")

    data object SignIn : NoArgumentsDestination("sign_in")

    data object RegistrationFlow : Destination("registration_flow", "step") {
        override fun generateFullStrung(vararg args: String): String = route.appendParams(
            "step" to args[0]
        )
    }

    data object MainFlow : NoArgumentsDestination("main_flow")

    data object CommonError : Destination("common_error", "error_model") {

        override fun generateFullStrung(vararg args: String): String = route.appendParams(
            "error_model" to args[0]
        )
    }
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