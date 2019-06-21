package com.roix.semenbelalov.roixcleanmvi.buissness.main.models

sealed class Event(val step: Int) {
    class SingleEvent(step: Int) : Event(step)
    class MultiEvent(step: Int) : Event(step)

}


class Update(val result: String)

