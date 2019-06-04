package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.navigation

import androidx.lifecycle.LiveData
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.navigation.commands.ScreenCommand

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

interface IViewModelNavigationDelegate {
    val navigationLiveData: LiveData<ScreenCommand>

    fun nextScreen(screenId: Int)
    fun nextScreen(screenId: Int, params: Any)
    fun goForward()
    fun goBack()
    fun backTo(screenId: Int)
}