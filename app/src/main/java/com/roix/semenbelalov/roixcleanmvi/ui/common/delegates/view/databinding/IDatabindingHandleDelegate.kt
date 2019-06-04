package com.roix.semenbelalov.roixcleanmvi.ui.common.delegates.view.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import com.roix.semenbelalov.roixcleanmvi.ui.common.viewmodels.BaseViewModel

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

interface IDatabindingHandleDelegate<DataBinding : ViewDataBinding, ViewModel : BaseViewModel> {
    fun initBinding(activity: AppCompatActivity, layoutId: Int, viewmodel: ViewModel? = null): DataBinding
    fun initBinding(
        activity: AppCompatActivity,
        layoutId: Int,
        inflater: LayoutInflater,
        container: ViewGroup?,
        viewmodel: ViewModel? = null
    ): DataBinding

    var binding: DataBinding

}