package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

interface IUIChannelBuilder {

    fun <T> from(lifecycleOwner: LifecycleOwner, liveData: LiveData<T>): EventBuilderOneParam<T>

    fun <T, R> from(
        lifecycleOwner: LifecycleOwner,
        liveData: LiveData<T>,
        liveData2: LiveData<R>
    ): EventBuilderTwoParams<T, R>

    fun <T, R> EventBuilder<T>.with(actor: Actor<T, R>): ActorBuilder<T, R>

}