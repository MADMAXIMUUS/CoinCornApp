package ru.coincorn.app.navigation

import kotlinx.coroutines.channels.Channel

interface AppNavigator {
    val navigationChannel: Channel<NavigationIntent>

    suspend fun backTo(
        route: Destination? = null,
    )

    suspend fun back()

    suspend fun navigateTo(
        route: Destination,
    )

    suspend fun newRootScreen(
        route: Destination,
    )

    suspend fun replaceScreen(
        route: Destination,
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