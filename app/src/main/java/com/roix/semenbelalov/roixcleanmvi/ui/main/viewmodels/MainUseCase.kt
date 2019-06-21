package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

import android.util.Log
import com.roix.cleanmvi.buissness.UseCase

class MainUseCase : UseCase<MainEvents, MainUpdates> {
    override suspend fun go(): (MainEvents) -> MainUpdates? = {
        Log.d("roix mvi", "MainUseCase update $it")
        Thread.sleep(2000)
        BuzzUpdate("buzz")
    }
}