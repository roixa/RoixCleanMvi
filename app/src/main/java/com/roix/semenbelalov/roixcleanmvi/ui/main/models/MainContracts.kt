package com.roix.semenbelalov.roixcleanmvi.ui.main.models

import com.roix.semenbelalov.roixcleanmvi.data.models.MainItem


sealed class UIEvent {
    object OnAddSingleClicked : UIEvent()
    object OnAddMultiClicked : UIEvent()

}

data class State(val results: List<MainItem>)

