package ru.coincorn.app.core.notification

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow

class PushEventBus {
    private val eventsRelay: Channel<PushEvent> = Channel(
        capacity = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )
    fun emitEvent(event: PushEvent) = eventsRelay.trySend(event)
    val events: Flow<PushEvent> = eventsRelay.receiveAsFlow()
}

sealed class PushEvent {
    data class AuthLoginCode(val loginCode: String) : PushEvent()
}