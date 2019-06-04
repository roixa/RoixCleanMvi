package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.error

import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.view.sub_livedata.ILiveDataSubscriptionDelegate

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

interface IErrorHandleViewDelegate {
    fun initErrorHandle(subscription: ILiveDataSubscriptionDelegate, errorSource: IErrorHandleViewModelDelegate)
    fun handleError(error: Throwable)
}