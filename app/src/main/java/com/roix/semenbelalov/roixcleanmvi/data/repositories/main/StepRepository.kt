package com.roix.semenbelalov.roixcleanmvi.data.repositories.main

import java.util.concurrent.atomic.AtomicInteger

class StepRepository : IStepRepository {

    private val step = AtomicInteger(0)

    override fun getNextStep(): Int {
        Thread.sleep(200)
        return step.incrementAndGet()

    }

}