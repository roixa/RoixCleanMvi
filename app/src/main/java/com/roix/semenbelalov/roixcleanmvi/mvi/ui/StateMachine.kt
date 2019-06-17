package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import kotlinx.coroutines.flow.Flow

interface StateMachine<Event, Update, State> {

    fun publish(event: Event)

    val handleEvent:(event: Event)-> Flow<Update>

    val updateState:(lastState:State, update: Update)-> State

    fun close()

}