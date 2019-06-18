package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import kotlinx.coroutines.flow.Flow

interface StateMachine<Event, Update, State> {

    fun publish(event: Event) {}

    suspend fun handleUpdates(event: Event): Flow<Update>?

    suspend fun handleUpdate(event: Event): Update?

    val updateState:(lastState:State, update: Update)-> State

    fun bind(state: State)

    fun close() {}

}