package com.roix.semenbelalov.roixcleanmvi.ui.common.viewmodels

import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.list.IListHandleViewModelDelegate
import com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.vvm.list.ListHandleViewModelDelegate

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

abstract class BaseListViewModel<Item> : BaseViewModel()
    , IListHandleViewModelDelegate<Item> by ListHandleViewModelDelegate<Item>()