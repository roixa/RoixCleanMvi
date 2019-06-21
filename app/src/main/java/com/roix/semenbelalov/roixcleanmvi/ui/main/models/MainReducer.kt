package com.roix.semenbelalov.roixcleanmvi.ui.main.models

import com.roix.cleanmvi.ui.Reducer
import com.roix.semenbelalov.roixcleanmvi.buissness.main.models.Update
import com.roix.semenbelalov.roixcleanmvi.data.models.MainItem

class MainReducer : Reducer<Update, State> {
    override fun go(): (State, Update) -> State = { state, update ->
        val step = state.step + 1
        val newList = state.results.toMutableList()
        newList.add(MainItem("$step ${update.result}"))
        state.copy(step = step, results = newList)
    }
}