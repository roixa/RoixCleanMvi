package com.roix.semenbelalov.roixcleanmvi.buissness.main

import com.roix.cleanmvi.buissness.FlowUseCase
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Event
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Update
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MultiAddUseCase(val singleAddUseCase: SingleAddUseCase) : FlowUseCase<Event, Update> {

    override suspend fun go(): (event: Event) -> Flow<Update>? = { event ->
        flow {
            for (i in 0..5) {
                val count = event.step + i
                singleAddUseCase.go().invoke(Event.MultiEvent(count))?.let { emit(it) }
            }

        }
    }
}