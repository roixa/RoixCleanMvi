package com.roix.semenbelalov.roixcleanmvi.mvi.channel

import com.roix.semenbelalov.roixcleanmvi.mvi.buissness.UseCase
import com.roix.semenbelalov.roixcleanmvi.mvi.ui.Reducer

interface IRoixChannel<T> {

    fun <U> go(useCase: UseCase<T, U>): IRoixChannel<U>

    fun <U> go(useCase: suspend (T) -> U?): IRoixChannel<U>

    fun <S> to(initialState: S? = null, reducer: Reducer<T, S>): IRoixChannel<S>

    fun <S> to(initialState: S? = null, reducer: (S, T) -> S): IRoixChannel<S>

    fun pub(event: T)

    fun sub(subber: (T) -> Unit)

}