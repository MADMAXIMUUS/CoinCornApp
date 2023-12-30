package ru.coincorn.app.navigation

sealed class Destination(protected val route: String, vararg params: String) {
    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    open operator fun invoke(): String = route

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        override operator fun invoke(): String = route
    }

    data object Root : NoArgumentsDestination("root")

    data object Intro : NoArgumentsDestination("intro")

    data object AuthFlow : NoArgumentsDestination("auth_flow")

    data object Welcome : NoArgumentsDestination("welcome")

    data object SignUp : NoArgumentsDestination("sign_up")

    data object SignIn : NoArgumentsDestination("sign_in")

    data object MainFlow : NoArgumentsDestination("main_flow")
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