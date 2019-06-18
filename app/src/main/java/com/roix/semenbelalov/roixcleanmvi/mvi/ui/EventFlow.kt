package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import com.roix.semenbelalov.roixcleanmvi.mvi.buissness.IInteractors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

class EventFlow<E>(val scope: CoroutineScope) {

    val events = Channel<E>(Channel.CONFLATED)

    fun <U> from(iInteractors: IInteractors<E, U>): UpdateFlow<E, U> = UpdateFlow(this, scope, iInteractors)

    fun publish(event: E) {
        scope.launch {
            events.send(event)
        }
    }
}