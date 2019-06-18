package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlin.coroutines.CoroutineContext

open class ClosableCoroutineScope(iCoroutineOwner: ICoroutineOwner) : CoroutineScope {

    override val coroutineContext: CoroutineContext = iCoroutineOwner.context

    init {
        iCoroutineOwner.addCoroutineScope { close() }
    }

    fun close() {
        coroutineContext.cancel()
    }
}