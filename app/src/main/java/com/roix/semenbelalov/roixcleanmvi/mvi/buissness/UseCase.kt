package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

interface UseCase<From, To> {
    suspend fun go(): (event: From) -> To
}