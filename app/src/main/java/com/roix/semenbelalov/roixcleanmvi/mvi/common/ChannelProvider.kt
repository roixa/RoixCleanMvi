package com.roix.semenbelalov.roixcleanmvi.mvi.common

import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel

open class ChannelProvider<Event>(val channel: BroadcastChannel<Event>)