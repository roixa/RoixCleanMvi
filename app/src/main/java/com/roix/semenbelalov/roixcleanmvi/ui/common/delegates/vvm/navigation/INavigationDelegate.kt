package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.navigation

import android.view.View
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.view.sub_livedata.ILiveDataSubscriptionDelegate

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

interface INavigationDelegate {
    fun initNavigationHandle(
        view: View,
        subscription: ILiveDataSubscriptionDelegate,
        source: IViewModelNavigationDelegate
    )

    fun nextScreen(screenId: Int, params: Any?)
    fun goForward()
    fun goBack()
    fun backTo(screenId: Int)
}