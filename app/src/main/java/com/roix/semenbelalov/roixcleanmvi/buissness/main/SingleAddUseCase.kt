package com.roix.semenbelalov.roixcleanmvi.buissness.main

import com.roix.cleanmvi.buissness.UseCase
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Event
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Update
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IBuzzRepository
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IFuzzRepository
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IStepRepository

class SingleAddUseCase(
    val fuzzRepository: IFuzzRepository,
    val buzzRepository: IBuzzRepository,
    val stepRepository: IStepRepository
) :
    UseCase<Event.SingleEvent, Update> {
    override suspend fun go(): (event: Event) -> Update? = {
        val count = stepRepository.getNextStep()
            var ret = "$count "
            if (count % 3 == 0) {
                ret += fuzzRepository.getFuzz()
            }
            if (count % 5 == 0) {
                ret += buzzRepository.getBuzz()
            }
            Update(ret)

    }
}