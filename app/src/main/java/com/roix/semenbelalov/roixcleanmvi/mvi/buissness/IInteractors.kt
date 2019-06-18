package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

interface IInteractors<E, U> {
    suspend fun go(): (E) -> U?
}