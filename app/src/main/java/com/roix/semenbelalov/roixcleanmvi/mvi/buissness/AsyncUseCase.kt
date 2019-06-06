package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

import io.reactivex.Single

interface AsyncUseCase<From, To> {
    val go: (From) -> Single<To>
}