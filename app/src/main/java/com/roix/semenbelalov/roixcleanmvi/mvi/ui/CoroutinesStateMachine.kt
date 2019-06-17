package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
class CoroutinesStateMachine(
    initialState: State
) :
    StateMachine<CoroutinesStateMachine.Event, CoroutinesStateMachine.Update, CoroutinesStateMachine.State>,
    CoroutineScope {

    interface Event
    interface Update
    interface State

    override val coroutineContext: CoroutineContext = SupervisorJob() + Dispatchers.IO

    private val mEvents = Channel<Event>(Channel.CONFLATED)

    private val updateFlows = Channel<Flow<Update>>(Channel.UNLIMITED)

    private val mStates = ConflatedBroadcastChannel(initialState)

    private val state get() = mStates.value

    init {
        launch {
            mEvents.consumeEach { event ->
                updateFlows.send(handleEvent(event))
            }
        }
        launch {
            updateFlows.consumeEach { updates ->
                updates.collect { update ->
                    mStates.send(updateState(state, update))
                }
            }
        }
    }

    override fun publish(event: Event) {
        launch {
            mEvents.send(event)
        }
    }

    override lateinit var handleEvent: (event: Event) -> Flow<Update>

    override lateinit var updateState: (lastState: State, update: Update) -> State

    override fun close() {
        coroutineContext.cancel()
    }
}