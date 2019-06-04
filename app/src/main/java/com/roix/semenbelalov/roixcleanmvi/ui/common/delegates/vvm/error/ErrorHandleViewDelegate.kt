package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.error

import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.view.sub_livedata.ILiveDataSubscriptionDelegate

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

class ErrorHandleViewDelegate : IErrorHandleViewDelegate {

    override fun initErrorHandle(
        subscription: ILiveDataSubscriptionDelegate,
        errorSource: IErrorHandleViewModelDelegate
    ) {
        subscription.apply {
            errorSource.errorLiveData.sub {
                handleError(it)
            }
        }
    }

    override fun handleError(error: Throwable) {

    }
}