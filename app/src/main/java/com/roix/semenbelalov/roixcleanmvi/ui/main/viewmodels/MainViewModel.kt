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

    init {
        go { convertUiToEvents(it) }.run {
            go(multiAddUseCase)
                .with(go(singleAddUseCase))

        }
            .reduce(State(emptyList()), MainReducer())
            .sub {
                items.update(it.results)
                Log.d("roix mvi", "sub" + Thread.currentThread().name)
            }
    }

    fun onClickedAddSingle() {
        pub(UIEvent.OnAddSingleClicked)
    }

    fun onClickedAddMulty() {
        pub(UIEvent.OnAddMultiClicked)
    }



    private fun convertUiToEvents(uiEvent: UIEvent): Event {
        return when (uiEvent) {
            is UIEvent.OnAddSingleClicked -> Event.SingleEvent
            else -> Event.MultiEvent
        }
    }
}
