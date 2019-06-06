package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

import com.roix.semenbelalov.roixcleanmvi.mvi.common.ChannelProvider

class BuissnessRxChannelBuilder : IBuissnessChannelBuilder {
    override fun <Action> from(channelProvider: ChannelProvider<Action>): StartedUseCaseBuilder<Action> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun go() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <From, To> use(useCase: UseCase<From, To>): UseCaseBuilder<From, To> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <From, To> UseCaseBuilder<From, To>.to(useCase: UseCase<From, To>): UseCaseBuilder<From, To> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}