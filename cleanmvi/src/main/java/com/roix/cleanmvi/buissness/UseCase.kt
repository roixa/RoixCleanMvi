package com.roix.cleanmvi.buissness

interface UseCase<E, U> {
    suspend fun go(): (event: E) -> U?
}