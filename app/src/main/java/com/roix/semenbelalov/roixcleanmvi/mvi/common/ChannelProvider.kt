package com.roix.semenbelalov.roixcleanmvi.mvi.common

import io.reactivex.Flowable

open class ChannelProvider<Event>(val channel: Flowable<Event>)