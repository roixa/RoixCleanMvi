package com.roix.semenbelalov.roixcleanmvi.ui.common.viewmodels

import android.app.Application
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.error.ErrorHandleViewModelDelegate
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.error.IErrorHandleViewModelDelegate
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.loading.ILoadingViewModelDelegate
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.loading.LoadingViewModelDelegate
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.message.IShowMessageHandleViewModelDelegate
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.message.ShowMessageHandleViewModelDelegate
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.navigation.IViewModelNavigationDelegate
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.navigation.ViewModelNavigationDelegate

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
abstract class BaseViewModel : ViewModel()
    , IErrorHandleViewModelDelegate by ErrorHandleViewModelDelegate()
    , IShowMessageHandleViewModelDelegate by ShowMessageHandleViewModelDelegate()
    , ILoadingViewModelDelegate by LoadingViewModelDelegate()
    , IViewModelNavigationDelegate by ViewModelNavigationDelegate()
     {

    private var viewsCount = 0

    @CallSuper
    fun onBindView(application: Application) {
        if (viewsCount == 0) {
            onBindFirstView(application)
        }
        viewsCount++
    }

    @CallSuper
    open fun onBindFirstView() {

    }

    @CallSuper
    fun onBindFirstView(application: Application) {
        onBindFirstView()
    }


}
