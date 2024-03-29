package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.loading

import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.view.sub_livedata.ILiveDataSubscriptionDelegate

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

class LoadingHandleDelegate : ILoadingHandleDelegate {

    override fun initLoadingHandle(subscription: ILiveDataSubscriptionDelegate, source: ILoadingViewModelDelegate) {
        subscription.apply {
            source.loadingLiveData.sub {
                handleProgress(it)
            }
        }

    }

    override fun handleProgress(isProgress: Boolean) {
    }
}