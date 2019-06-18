package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

import android.util.Log
import com.roix.semenbelalov.roixcleanmvi.mvi.ui.Reducer

class MainReducer : Reducer<MainUpdates, MainStates> {
    override fun go(): (MainStates, MainUpdates) -> MainStates = { mainStates, mainUpdates ->
        Log.d("roix mvi", "MainReducer state $mainStates update$mainUpdates")
        mainStates
    }
}