package com.roix.semenbelalov.roixcleanmvi.mvi.ui

abstract class Actor<Event, Action> {
    abstract val act: (Event) -> Action
}