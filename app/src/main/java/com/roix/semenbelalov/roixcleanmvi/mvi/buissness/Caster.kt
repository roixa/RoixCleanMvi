package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

class Caster<E, S> : UseCase<E, S> {
    override suspend fun go(): (event: E) -> S? = {
        it as? S
    }
}