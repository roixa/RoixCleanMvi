package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

import com.roix.semenbelalov.roixcleanmvi.mvi.common.ChannelProvider

interface IBuissnessChannelBuilder {
    fun <Action> from(channelProvider: ChannelProvider<Action>): StartedUseCaseBuilder<Action>
    fun go()
    fun <From, To> use(useCase: UseCase<From, To>): UseCaseBuilder<From, To>
    fun <From, To> UseCaseBuilder<From, To>.to(useCase: UseCase<From, To>): UseCaseBuilder<From, To>
}