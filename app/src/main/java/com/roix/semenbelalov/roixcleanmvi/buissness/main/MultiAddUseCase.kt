package com.roix.semenbelalov.roixcleanmvi.buissness.main

import com.roix.cleanmvi.buissness.FlowUseCase
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Event
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Update
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IBuzzRepository
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IFuzzRepository
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IStepRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MultiAddUseCase(
    val fuzzRepository: IFuzzRepository,
    val buzzRepository: IBuzzRepository,
    val stepRepository: IStepRepository
) : FlowUseCase<Event, Update> {

    override suspend fun go(): (event: Event) -> Flow<Update>? = { event ->
        if (event is Event.MultiEvent) {
            flow {
                repeat(5) {
                    oneStep(event)?.let {
                        emit(it)
                    }
                }
            }
        } else null
    }

    fun oneStep(event: Event): Update? {
        val count = stepRepository.getNextStep()
        var ret = "$count "
        if (count % 3 == 0) {
            ret += fuzzRepository.getFuzz()
        }
        if (count % 5 == 0) {
            ret += buzzRepository.getBuzz()
        }
        return Update(ret)
    }

}