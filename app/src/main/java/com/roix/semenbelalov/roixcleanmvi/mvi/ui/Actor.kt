package com.roix.semenbelalov.roixcleanmvi.mvi.ui

interface Actor<Event, Action> {
    fun go(): (Event) -> Action
}