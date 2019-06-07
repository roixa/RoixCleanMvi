package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

import com.roix.semenbelalov.roixcleanmvi.mvi.common.ChannelProvider

abstract class RxInteractors<From, Inner, To> : IInteractors<From, Inner, To> {

    lateinit var channelProvider: ChannelProvider<From>

    fun start(channelProvider: ChannelProvider<From>) {
        this.channelProvider = channelProvider
        go()
    }

    fun <T> use(useCase: UseCase<From, T>): UseCaseBuilder<From, T> = UseCaseBuilder(channelProvider, useCase)

    fun <F, I, T> UseCaseBuilder<F, I>.to(useCase: UseCase<I, T>) = UseCaseBuilder(this, useCase)

}