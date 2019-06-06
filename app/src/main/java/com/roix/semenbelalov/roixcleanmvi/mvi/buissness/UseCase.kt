package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

interface UseCase<From, To> {
    val go: (From) -> To
}