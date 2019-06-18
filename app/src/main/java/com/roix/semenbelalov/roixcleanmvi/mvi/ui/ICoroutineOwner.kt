package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import kotlin.coroutines.CoroutineContext

interface ICoroutineOwner {
    val context: CoroutineContext
    fun addCoroutineScope(closer: () -> Unit)
    fun closeCoroutineScopes()
}