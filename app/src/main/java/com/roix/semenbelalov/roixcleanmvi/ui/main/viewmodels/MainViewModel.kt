package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

import android.util.Log
import com.roix.cleanmvi.channel.IRoixChannel
import com.roix.cleanmvi.channel.RoixChannel
import com.roix.semenbelalov.roixcleanmvi.buissness.main.MultiAddUseCase
import com.roix.semenbelalov.roixcleanmvi.buissness.main.SingleAddUseCase
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Event
import com.roix.semenbelalov.roixcleanmvi.data.models.MainItem
import com.roix.semenbelalov.roixcleanmvi.ui.common.viewmodels.BaseListViewModel
import com.roix.semenbelalov.roixcleanmvi.ui.main.models.MainReducer
import com.roix.semenbelalov.roixcleanmvi.ui.main.models.State
import com.roix.semenbelalov.roixcleanmvi.ui.main.models.UIEvent

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
class MainViewModel(singleAddUseCase: SingleAddUseCase, multiAddUseCase: MultiAddUseCase) :
    BaseListViewModel<MainItem>(),
    IRoixChannel<UIEvent> by RoixChannel() {

    var step = 0

    init {
        go { convertUiToEvents(it) }
            .go(multiAddUseCase)
            .reduce(State(0, emptyList()), MainReducer())
            .sub {
                items.update(it.results)
                step = it.results.size
                Log.d("roix mvi", it.toString())
            }
    }

    fun onEvent() {
        pub(UIEvent.OnAddSingleClicked)
    }


    private fun convertUiToEvents(uiEvent: UIEvent): Event {
        return if (uiEvent is UIEvent.OnAddSingleClicked) {
            Event.SingleEvent(step)
        } else {
            Event.MultiEvent(step)

        }

    }
}
