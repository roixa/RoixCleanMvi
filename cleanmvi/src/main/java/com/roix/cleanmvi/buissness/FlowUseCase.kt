package com.roix.cleanmvi.buissness

import kotlinx.coroutines.flow.Flow

interface FlowUseCase<E, U> {
    suspend fun go(): (event: E) -> Flow<U>?
}