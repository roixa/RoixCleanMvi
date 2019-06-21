package com.roix.cleanmvi.test

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class Binder<S>(scope: CoroutineScope, channel: ReceiveChannel<S>, binder: (S) -> Unit) {
    init {
        scope.launch {
            channel.consumeEach { state ->
                binder.invoke(state)
            }
        }
    }

}