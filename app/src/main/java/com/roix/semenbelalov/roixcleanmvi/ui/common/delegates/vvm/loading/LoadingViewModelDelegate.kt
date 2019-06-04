package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.loading

import com.roix.semenbelalov.roixcleanmvi.ui.common.loading.LoadingLiveData

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

class LoadingViewModelDelegate : ILoadingViewModelDelegate {

    override val loadingLiveData: LoadingLiveData = LoadingLiveData()

    override fun onStartLoad() = loadingLiveData.onStartLoad()

    override fun onEndLoad() = loadingLiveData.onEndLoad()

}