package com.roix.semenbelalov.roixcleanmvi.mvi.ui

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.roix.semenbelalov.roixcleanmvi.mvi.common.ChannelProvider
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

sealed class EventBuilder<Event>(channel: Flowable<Event>) : ChannelProvider<Event>(channel)

class EventBuilderOneParam<Event>(liveData: LiveData<Event>, lifecycleOwner: LifecycleOwner) :
    EventBuilder<Event>(liveData.toFlowable(lifecycleOwner))

class EventBuilderTwoParams<Event1, Event2>(
    liveData: LiveData<Event1>,
    liveData2: LiveData<Event2>,
    lifecycleOwner: LifecycleOwner
) :
    EventBuilder<Pair<Event1, Event2>>(
        liveData.toFlowable(lifecycleOwner).zipWith(liveData2.toFlowable(lifecycleOwner),
            BiFunction { t1: Event1, t2: Event2 ->
                Pair(
                    t1,
                    t2
                )
            })
    )

class ActorBuilder<T, R>(channelProvider: ChannelProvider<T>, actor: Actor<T, R>) :
    ChannelProvider<R>(channelProvider.channel.map(actor.act))

fun <T> LiveData<T>.toFlowable(lifecycleOwner: LifecycleOwner): Flowable<T> {
    return Flowable.fromPublisher(LiveDataReactiveStreams.toPublisher(lifecycleOwner, this))
}

