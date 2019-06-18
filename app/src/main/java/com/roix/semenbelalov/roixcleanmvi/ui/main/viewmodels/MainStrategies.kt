package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

sealed class MainEvents
class OnButtonClicked : MainEvents()
data class OnInputData(val text: String) : MainEvents()

sealed class MainUpdates
data class FuzzUpdate(val text: String) : MainUpdates()
data class BuzzUpdate(val text: String) : MainUpdates()

sealed class MainStates()
data class Started(val text: String) : MainStates()
data class Process(val text: String) : MainStates()