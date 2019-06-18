package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class CoroutinesStateMachine<E, U, S>(
    initialState: S,
    stateMachine: StateMachine<E, U, S>
) :
    StateMachine<E, U, S> by stateMachine,
    CoroutineScope {


    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    private val mEvents = Channel<E>(Channel.CONFLATED)

    private val updateFlows = Channel<Flow<U>>(Channel.UNLIMITED)

    private val updates = Channel<U>(Channel.UNLIMITED)

    private val mStates = ConflatedBroadcastChannel(initialState)

    private val state get() = mStates.value

    init {
        launch {
            mEvents.consumeEach { event ->
                handleUpdates(event)?.let { update ->
                    updateFlows.send(update)
                }
                handleUpdate(event)?.let { update ->
                    updates.send(update)
                }
            }
        }
        launch {
            updateFlows.consumeEach { updates ->
                updates.collect { update ->
                    mStates.send(updateState(state, update))
                }
            }
        }
        launch {
            updates.consumeEach { update ->
                mStates.send(updateState(state, update))
            }
        }
        launch {
            mStates.openSubscription().consumeEach {state->
                bind(state)
            }
        }
    }

    override fun publish(event: E) {
        launch {
            mEvents.send(event)
        }
    }

    override fun close() {
        coroutineContext.cancel()
    }
}