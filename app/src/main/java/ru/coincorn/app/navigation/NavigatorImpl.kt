package ru.coincorn.app.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class AppNavigatorImpl @Inject constructor() : AppNavigator {
    override val navigationChannel = Channel<NavigationIntent>(
        capacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )

    override suspend fun backTo(route: Destination?) {
        navigationChannel.trySend(
            NavigationIntent.NavigateBack(
                route = route?.invoke()
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

    override suspend fun navigateTo(route: Destination) {
        navigationChannel.trySend(
            NavigationIntent.NavigateTo(
                route = route()
            )
        )
    }

    override suspend fun newRootScreen(route: Destination) {
        navigationChannel.trySend(
            NavigationIntent.Root(
                route = route()
            )
        )
    }

    override suspend fun replaceScreen(route: Destination) {
        navigationChannel.trySend(
            NavigationIntent.Replace(
                route = route(),
            )
        )
    }
}