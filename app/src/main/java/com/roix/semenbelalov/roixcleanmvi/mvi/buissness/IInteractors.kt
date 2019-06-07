package com.roix.semenbelalov.roixcleanmvi.mvi.buissness

interface IInteractors<From, Inner, To> {
    fun go(): (From) -> UseCase<Inner, To>
}