package com.roix.semenbelalov.roixcleanmvi.buissness.main

import com.roix.cleanmvi.buissness.UseCase
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Event
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Update
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IBuzzRepository
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IFuzzRepository

class SingleAddUseCase(val fuzzRepository: IFuzzRepository, val buzzRepository: IBuzzRepository) :
    UseCase<Event, Update> {
    override suspend fun go(): (event: Event) -> Update? = {
            val count = it.step
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