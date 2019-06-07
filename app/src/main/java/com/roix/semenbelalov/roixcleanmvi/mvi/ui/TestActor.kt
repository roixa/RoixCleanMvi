package com.roix.semenbelalov.roixcleanmvi.mvi.ui

class TestActor : Actor<String, Int> {
    override fun go(): (String) -> Int = { s ->
        0
    }
}