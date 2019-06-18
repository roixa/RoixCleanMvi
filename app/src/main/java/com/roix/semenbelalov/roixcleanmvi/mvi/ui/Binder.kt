package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class Binder<S>(scope: CoroutineScope, stateFlow: StateFlow<*, S>, binder: (S) -> Unit) {
    init {
        scope.launch {
            stateFlow.states.openSubscription().consumeEach { state ->
                binder.invoke(state)
            }
        }
    }

}