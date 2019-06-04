package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.list

import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.view.sub_livedata.ILiveDataSubscriptionDelegate

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

interface IListHandleViewDelegate<Item> {
    fun initListHandle(
        viewProvider: ListViewProvider,
        subscription: ILiveDataSubscriptionDelegate,
        source: IListHandleViewModelDelegate<Item>
    )
}