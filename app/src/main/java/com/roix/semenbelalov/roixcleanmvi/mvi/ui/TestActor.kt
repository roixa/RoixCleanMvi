package com.roix.semenbelalov.roixcleanmvi.mvi.ui

class TestActor : Actor<String, Int>() {
    override val act: (String) -> Int = { s ->
        0
    }
}