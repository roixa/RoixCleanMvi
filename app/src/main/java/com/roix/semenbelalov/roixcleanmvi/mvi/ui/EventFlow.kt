package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import com.roix.semenbelalov.roixcleanmvi.mvi.buissness.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class EventFlow<E>(val scope: CoroutineScope) {

    private val events = Channel<E>(Channel.CONFLATED)

    fun <U> from(useCase: UseCase<E, U>): UpdateFlow<E, U> = UpdateFlow(events, scope, useCase)

    fun <U> from(useCase: suspend (E) -> U?): UpdateFlow<E, U> = UpdateFlow(events, scope, useCase)


    fun publish(event: E) {
        scope.launch {
            events.send(event)
        }
    }
}