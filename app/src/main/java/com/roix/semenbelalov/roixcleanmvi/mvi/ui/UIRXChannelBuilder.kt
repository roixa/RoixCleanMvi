package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

class UIRXChannelBuilder : IUIChannelBuilder {

    override fun <T> from(lifecycleOwner: LifecycleOwner, liveData: LiveData<T>): EventBuilderOneParam<T> =
        EventBuilderOneParam(liveData, lifecycleOwner)

    override fun <T, R> from(
        lifecycleOwner: LifecycleOwner,
        liveData: LiveData<T>,
        liveData2: LiveData<R>
    ): EventBuilderTwoParams<T, R> = EventBuilderTwoParams(liveData, liveData2, lifecycleOwner)

    override fun <T, R> EventBuilder<T>.with(actor: Actor<T, R>): ActorBuilder<T, R> = ActorBuilder(this, actor)
}