package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

interface UseCase<From, To> {
    fun go(): (From) -> To
}