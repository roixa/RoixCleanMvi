package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

import io.reactivex.Flowable

interface AsyncRxUseCase<From, To> {
    val go: (From) -> Flowable<To>
}