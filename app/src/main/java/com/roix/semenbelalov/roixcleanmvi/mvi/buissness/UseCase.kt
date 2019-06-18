package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

interface UseCase<E, U> {
    suspend fun go(): (event: E) -> U?
}