package com.roix.semenbelalov.roixcleanmvi.mvi.ui

interface Reducer<U, S> {
    fun go(): (S, U) -> S
}