package com.roix.semenbelalov.roixcleanmvi.mvi.channel

import com.roix.semenbelalov.roixcleanmvi.mvi.buissness.UseCase
import com.roix.semenbelalov.roixcleanmvi.mvi.ui.Reducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class RoixChannel<T>(
    val backgroundScope: CoroutineScope,
    val uiScope: CoroutineScope,
    initial: T? = null
) : IRoixChannel<T> {

    private val channel = ConflatedBroadcastChannel(initial)

    @ObsoleteCoroutinesApi
    override fun <U> go(useCase: UseCase<T, U>): RoixChannel<U> = RoixChannel<U>(backgroundScope, uiScope).apply {
        val from = this@RoixChannel.channel
        val to = this.channel
        backgroundScope.launch {
            from.consumeEach { event ->
                if (event != null) {
                    useCase.go().invoke(event)?.let { to.send(it) }
                }
            }
        }
    }


    @ObsoleteCoroutinesApi
    override fun <U> go(useCase: suspend (T) -> U?): RoixChannel<U> = RoixChannel<U>(backgroundScope, uiScope).apply {
        val from = this@RoixChannel.channel
        val to = this.channel
        backgroundScope.launch {
            from.consumeEach { event ->
                if (event != null) {
                    useCase.invoke(event)?.let { to.send(it) }
                }
            }
        }
    }

    @ObsoleteCoroutinesApi
    override fun <S> to(initialState: S?, reducer: Reducer<T, S>) = RoixChannel<S>(backgroundScope, uiScope).apply {
        val from = this@RoixChannel.channel
        val to = this.channel
        val last = to.value ?: initialState
        backgroundScope.launch {
            from.consumeEach { update ->
                if (last != null && update != null) {
                    to.send(reducer.go().invoke(last, update))
                }
            }
        }

    }

    @ObsoleteCoroutinesApi
    override fun <S> to(initialState: S?, reducer: (S, T) -> S) = RoixChannel<S>(backgroundScope, uiScope).apply {
        val from = this@RoixChannel.channel
        val to = this.channel
        val last = to.value ?: initialState
        backgroundScope.launch {
            from.consumeEach { update ->
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