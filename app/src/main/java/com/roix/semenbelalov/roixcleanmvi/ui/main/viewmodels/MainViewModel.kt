package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

import com.roix.semenbelalov.roixcleanmvi.buissness.main.IMainInteractor
import com.roix.semenbelalov.roixcleanmvi.di.main.MainModule
import com.roix.semenbelalov.roixcleanmvi.ui.common.viewmodels.BaseListViewModel
import com.roix.semenbelalov.roixcleanmvi.data.models.MainItem
import javax.inject.Inject
import toothpick.config.Module

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
class MainViewModel : BaseListViewModel<MainItem>() {


    @Inject
    protected lateinit var interactor: IMainInteractor

    override val module: Module = MainModule()
}
