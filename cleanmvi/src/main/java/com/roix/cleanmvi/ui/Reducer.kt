package com.roix.cleanmvi.ui

interface Reducer<U, S> {
    fun go(): (S, U) -> S
}