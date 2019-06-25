package com.roix.cleanmvi.ui

interface Reducer<S, U> {
    fun go(): (S, U) -> S
}