package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<E, U> {
    suspend fun go(): (event: E) -> Flow<U>?
}