package ru.coincorn.app.core.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor() : AppNavigator {
    override val navigationChannel = Channel<NavigationIntent>(
        capacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )

    override suspend fun backTo(
        route: Destination,
        vararg args: String
    ) {
        navigationChannel.trySend(
            NavigationIntent.NavigateBack(
                route = route.generateFullStrung(*args)
            )
        )
    }

    override suspend fun back() {
        navigationChannel.trySend(
            NavigationIntent.NavigateBack(
                route = null
            )
        )
    }

    override suspend fun navigateTo(route: Destination, vararg args: String) {
        navigationChannel.trySend(
            NavigationIntent.NavigateTo(
                route = route.generateFullStrung(*args)
            )
        )
    }

    override suspend fun newRootScreen(
        route: Destination,
        vararg args: String
    ) {
        navigationChannel.trySend(
            NavigationIntent.Root(
                route = route.generateFullStrung(*args)
            )
        )
    }

    override suspend fun replaceScreen(
        route: Destination,
        vararg args: String
    ) {
        navigationChannel.trySend(
            NavigationIntent.Replace(
                route = route.generateFullStrung(*args)
            )
        )
    }
}