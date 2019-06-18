package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

import android.util.Log
import com.roix.semenbelalov.roixcleanmvi.mvi.buissness.IInteractors

class MainInteractors : IInteractors<MainEvents, MainUpdates> {
    override suspend fun go(): (MainEvents) -> MainUpdates? = {
        Log.d("roix mvi", "MainInteractors update $it")
        Thread.sleep(2000)
        BuzzUpdate("buzz")
    }
}