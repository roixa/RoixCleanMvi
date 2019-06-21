package com.roix.cleanmvi.channel

import com.roix.cleanmvi.buissness.FlowUseCase
import com.roix.cleanmvi.buissness.UseCase
import com.roix.cleanmvi.ui.Reducer
import kotlinx.coroutines.CoroutineScope

interface IRoixChannel<T> {

    val backgroundScope: CoroutineScope

    val uiScope: CoroutineScope

    fun <U> go(useCase: UseCase<T, U>): IRoixChannel<U>

    fun <U> go(useCase: FlowUseCase<T, U>): IRoixChannel<U>

    fun <U> go(useCase: suspend (T) -> U?): IRoixChannel<U>

    fun <U> act(): IRoixChannel<U>

    fun with(channel: IRoixChannel<T>): IRoixChannel<T>

    fun <S> reduce(initialState: S?, reducer: Reducer<T, S>): IRoixChannel<S>

    fun <S> reduce(initialState: S?, reducer: (S, T) -> S): IRoixChannel<S>

    fun pub(event: T)

    fun sub(subber: (T) -> Unit)
}