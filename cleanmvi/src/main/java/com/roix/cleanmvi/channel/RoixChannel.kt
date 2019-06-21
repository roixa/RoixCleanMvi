package com.roix.cleanmvi.channel

import com.roix.cleanmvi.buissness.FlowUseCase
import com.roix.cleanmvi.buissness.UseCase
import com.roix.cleanmvi.ui.Reducer
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
class RoixChannel<T>(
    override val backgroundScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO),
    override val uiScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main),
    initial: T? = null
) : IRoixChannel<T> {

    private val channel = ConflatedBroadcastChannel(initial)


    @ObsoleteCoroutinesApi
    override fun <U> go(useCase: UseCase<T, U>): RoixChannel<U> = RoixChannel<U>(backgroundScope, uiScope).apply {
        backgroundScope.launch {
            val from = this@RoixChannel.channel
            val to = this@apply.channel
            from.consumeEach { event ->
                if (event != null) {
                    useCase.go().invoke(event)?.let { to.send(it) }
                }
            }
        }
    }

    override fun <U> go(useCase: FlowUseCase<T, U>): IRoixChannel<U> = RoixChannel<U>(backgroundScope, uiScope).apply {
        backgroundScope.launch {
            val from = this@RoixChannel.channel
            val to = this@apply.channel
            from.consumeEach { event ->
                if (event != null) {
                    useCase.go().invoke(event)?.let { flow ->
                        flow.collect { to.send(it) }
                    }
                }
            }
        }
    }

    @ObsoleteCoroutinesApi
    override fun <U> go(useCase: suspend (T) -> U?): RoixChannel<U> = RoixChannel<U>(backgroundScope, uiScope).apply {
        backgroundScope.launch {
            val from = this@RoixChannel.channel
            val to = this@apply.channel
            from.consumeEach { event ->
                if (event != null) {
                    useCase.invoke(event)?.let { to.send(it) }
                }
            }
        }
    }

    override fun <U> act() = RoixChannel<U>(backgroundScope, uiScope).apply {
        backgroundScope.launch {
            val from = this@RoixChannel.channel
            val to = this@apply.channel
            from.consumeEach { event ->
                val ret = event as? U
                if (ret != null) {
                    to.send(ret)
                }
            }
        }
    }

    override fun with(channel: IRoixChannel<T>): IRoixChannel<T> {
        val ret = RoixChannel<T>(backgroundScope, uiScope)
        sub {
            ret.pub(it)
        }
        channel.sub {
            ret.pub(it)
        }
        return ret
    }

    @ObsoleteCoroutinesApi
    override fun <S> reduce(initialState: S?, reducer: Reducer<T, S>) = RoixChannel<S>(backgroundScope, uiScope).apply {
        backgroundScope.launch {
            val from = this@RoixChannel.channel
            val to = this@apply.channel
            from.consumeEach { update ->
                val last = to.valueOrNull ?: initialState
                if (last != null && update != null) {
                    to.send(reducer.go().invoke(last, update))
                }
            }
        }

    }

    @ObsoleteCoroutinesApi
    override fun <S> reduce(initialState: S?, reducer: (S, T) -> S) = RoixChannel<S>(backgroundScope, uiScope).apply {
        backgroundScope.launch {
            val from = this@RoixChannel.channel
            val to = this@apply.channel
            from.consumeEach { update ->
                val last = to.valueOrNull ?: initialState
                if (last != null && update != null) {
                    to.send(reducer.invoke(last, update))
                }
            }
        }

    }


    override fun pub(event: T) {
        backgroundScope.launch {
            channel.send(event)
        }
    }


    override fun sub(subber: (T) -> Unit) {
        uiScope.launch {
            channel.openSubscription().consumeEach { state ->
                if (state != null) {
                    subber.invoke(state)
                }
            }
        }

    }
}