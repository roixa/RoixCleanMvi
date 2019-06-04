package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.loading

import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.view.sub_livedata.ILiveDataSubscriptionDelegate

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

interface ILoadingHandleDelegate {
    fun initLoadingHandle(subscription: ILiveDataSubscriptionDelegate, source: ILoadingViewModelDelegate)
    fun handleProgress(isProgress: Boolean)

}