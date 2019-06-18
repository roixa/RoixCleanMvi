package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class StateFlow<U, S>(val scope: CoroutineScope, initialState: S) {

    private val states = ConflatedBroadcastChannel(initialState)

    private val state get() = states.value

    constructor(
        channel: ReceiveChannel<U>,
        scope: CoroutineScope,
        initialState: S,
        reducer: Reducer<U, S>
    ) : this(scope, initialState) {
        scope.launch {
            channel.consumeEach { update ->
                states.send(reducer.go().invoke(state, update))
            }
        }

    }

    constructor(
        channel: ReceiveChannel<U>,
        scope: CoroutineScope,
        initialState: S,
        reducer: (S, U) -> S
    ) : this(scope, initialState) {
        scope.launch {
            channel.consumeEach { update ->
                states.send(reducer.invoke(state, update))
            }
        }

    }


    fun bind(binder: (S) -> Unit) = Binder(scope, states.openSubscription(), binder)
}