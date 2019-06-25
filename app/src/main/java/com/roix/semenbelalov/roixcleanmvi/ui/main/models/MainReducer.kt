package com.roix.semenbelalov.roixcleanmvi.ui.main.models

import com.roix.cleanmvi.ui.Reducer
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Update
import com.roix.semenbelalov.roixcleanmvi.data.models.MainItem

class MainReducer : Reducer<State, Update> {
    override fun go(): (State, Update) -> State = { state, update ->
        val newList = state.results.toMutableList()
        newList.add(MainItem(update.result))
        state.copy(results = newList)
    }
}