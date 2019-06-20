package com.roix.semenbelalov.roixcleanmvi.mvi.channel

import com.roix.semenbelalov.roixcleanmvi.mvi.buissness.FlowUseCase
import com.roix.semenbelalov.roixcleanmvi.mvi.buissness.UseCase
import com.roix.semenbelalov.roixcleanmvi.mvi.ui.Reducer
import kotlinx.coroutines.CoroutineScope

interface IRoixChannel<T> {

    val backgroundScope: CoroutineScope

    val uiScope: CoroutineScope

    fun <U> go(useCase: UseCase<T, U>): IRoixChannel<U>

    fun <U> go(useCase: FlowUseCase<T, U>): IRoixChannel<U>

    fun <U> go(useCase: suspend (T) -> U?): IRoixChannel<U>

    fun <U> cast(): IRoixChannel<U>

    fun with(channel: IRoixChannel<T>): IRoixChannel<T>

    fun <S> reduce(initialState: S?, reducer: Reducer<T, S>): IRoixChannel<S>

    fun <S> reduce(initialState: S?, reducer: (S, T) -> S): IRoixChannel<S>

    fun pub(event: T)

    fun sub(subber: (T) -> Unit)
}