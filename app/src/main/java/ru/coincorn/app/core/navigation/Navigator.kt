package ru.coincorn.app.core.navigation

import kotlinx.coroutines.channels.Channel

interface AppNavigator {
    val navigationChannel: Channel<NavigationIntent>

    suspend fun backTo(
        route: Destination,
        vararg args: String
    )

    suspend fun back()

    suspend fun navigateTo(
        route: Destination,
        vararg args: String
    )

    suspend fun newRootScreen(
        route: Destination,
        vararg args: String
    )

    suspend fun replaceScreen(
        route: Destination,
        vararg args: String
    )
}

sealed class NavigationIntent {
    data class NavigateBack(
        val route: String? = null,
    ) : NavigationIntent()

    data class NavigateTo(
        val route: String,
    ) : NavigationIntent()

    data class Replace(
        val route: String,
    ) : NavigationIntent()

    data class Root(
        val route: String
    ) : NavigationIntent()
}