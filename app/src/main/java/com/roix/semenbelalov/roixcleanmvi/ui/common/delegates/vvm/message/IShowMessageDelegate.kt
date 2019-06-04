package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.message

import android.content.Context
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.view.sub_livedata.ILiveDataSubscriptionDelegate

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

interface IShowMessageDelegate {
    fun initShowMessageHandle(
        context: Context,
        subscription: ILiveDataSubscriptionDelegate,
        source: IShowMessageHandleViewModelDelegate
    )

    fun showMessage(text: String)
}