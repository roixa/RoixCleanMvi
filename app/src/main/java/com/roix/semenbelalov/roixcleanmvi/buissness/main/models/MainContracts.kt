package com.roix.semenbelalov.roixcleanmvi.buissness.main.models

sealed class Event() {
    object SingleEvent : Event()
    object MultiEvent : Event()

}


class Update(val result: String)

