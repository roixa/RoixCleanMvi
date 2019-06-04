package com.roix.semenbelalov.roixcleanmvi.utils.ui

import androidx.lifecycle.MutableLiveData

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

fun <T> MutableLiveData<T>.singleEvent(param: T) {
    value = param
    value = null
}
