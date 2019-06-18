package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class StateFlow<U, S>(
    updateFlow: UpdateFlow<*, U>,
    val scope: CoroutineScope,
    initialState: S,
    reducer: Reducer<U, S>
) {

    val states = ConflatedBroadcastChannel(initialState)

    private val state get() = states.value


    init {
        scope.launch {
            updateFlow.updates.consumeEach { update ->
                states.send(reducer.go().invoke(state, update))
            }
        }
    }

    fun bind(binder: (S) -> Unit) = Binder(scope, this, binder)
}