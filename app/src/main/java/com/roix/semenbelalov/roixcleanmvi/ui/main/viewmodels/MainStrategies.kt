package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

sealed class MainEvents
class OnButtonClicked : MainEvents()
class OnInputData(val text: String) : MainEvents()

sealed class MainUpdates
class FuzzUpdate(val text: String) : MainUpdates()
class BuzzUpdate(val text: String) : MainUpdates()

sealed class MainStates(val text: String)
class Started(text: String) : MainStates(text)
class Process(text: String) : MainStates(text)