package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

import com.roix.semenbelalov.roixcleanmvi.mvi.common.ChannelProvider

class StartedUseCaseBuilder<From>(channelProvider: ChannelProvider<From>) :
    ChannelProvider<From>(channelProvider.channel)

class UseCaseBuilder<From, To>(channelProvider: ChannelProvider<From>, useCase: UseCase<From, To>) :
    ChannelProvider<To>(channelProvider.channel.map(useCase.go())) {
}

