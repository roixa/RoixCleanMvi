package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import com.roix.semenbelalov.roixcleanmvi.mvi.buissness.UseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class UpdateFlow<E, U>(val scope: CoroutineScope) {

    private val updates = Channel<U>(Channel.UNLIMITED)

    constructor(channel: ReceiveChannel<E>, scope: CoroutineScope, useCase: UseCase<E, U>) : this(scope) {
        scope.launch {
            channel.consumeEach { event ->
                useCase.go().invoke(event)?.let { updates.send(it) }
            }
        }
    }

    constructor(channel: ReceiveChannel<E>, scope: CoroutineScope, useCase: suspend (E) -> U?) : this(scope) {
        scope.launch {
            channel.consumeEach { event ->
                useCase.invoke(event)?.let { updates.send(it) }
            }
        }
    }

    fun <S> to(initialState: S, reducer: Reducer<U, S>) = StateFlow(updates, scope, initialState, reducer)

    fun <S> to(initialState: S, reducer: (S, U) -> S) = StateFlow(updates, scope, initialState, reducer)

}