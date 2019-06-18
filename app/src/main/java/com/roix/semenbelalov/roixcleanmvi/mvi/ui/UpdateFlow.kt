package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import com.roix.semenbelalov.roixcleanmvi.mvi.buissness.IInteractors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class UpdateFlow<E, U>(eventFlow: EventFlow<E>, val scope: CoroutineScope, iInteractors: IInteractors<E, U>) {

    val updates = Channel<U>(Channel.UNLIMITED)

    init {
        scope.launch {
            eventFlow.events.consumeEach { event ->
                iInteractors.go().invoke(event)?.let { updates.send(it) }
            }
        }
    }

    fun <S> to(initialState: S, reducer: Reducer<U, S>) = StateFlow(this, scope, initialState, reducer)

}