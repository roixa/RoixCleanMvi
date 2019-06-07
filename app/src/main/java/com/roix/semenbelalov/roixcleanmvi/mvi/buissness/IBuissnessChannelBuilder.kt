package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

import io.reactivex.Flowable

interface IBuissnessChannelBuilder {
    fun <From, To> use(useCase: UseCase<From, To>): UseCaseBuilder<From, To>
    fun <From, To> use(flowable: Flowable<To>): UseCaseBuilder<From, To>

    fun <Prev, From, To> UseCaseBuilder<Prev, From>.to(useCase: UseCase<From, To>): UseCaseBuilder<From, To>
}