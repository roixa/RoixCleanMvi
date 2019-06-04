package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.viewmodel.di_toothpick

import com.roix.semenbelalov.roixcleanmvi.application.CommonApplication

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

interface IDIDelegate {
    fun initDIDelegate(application: CommonApplication, moduleProvider: ModuleProvider, injectingObject: Any)
    fun clearDiDelegate()
}