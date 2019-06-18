package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

class DefaultCoroutineOwner : ICoroutineOwner {

    val scopes = mutableListOf<() -> Unit>()

    override val context: CoroutineContext = SupervisorJob() + Dispatchers.IO

    override fun addCoroutineScope(closer: () -> Unit) {
        scopes.add(closer)
    }

    override fun closeCoroutineScopes() {
        scopes.forEach {
            it.invoke()
        }
        scopes.clear()
    }
}