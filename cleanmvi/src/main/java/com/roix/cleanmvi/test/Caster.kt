package com.roix.cleanmvi.test

import com.roix.cleanmvi.buissness.UseCase

class Caster<E, S> : UseCase<E, S> {
    override suspend fun go(): (event: E) -> S? = {
        it as? S
    }
}