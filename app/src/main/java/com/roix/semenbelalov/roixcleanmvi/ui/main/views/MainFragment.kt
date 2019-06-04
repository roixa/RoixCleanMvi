package com.roix.semenbelalov.roixcleanmvi.ui.main.views

import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.roix.semenbelalov.roixcleanmvi.data.models.MainItem
import com.roix.semenbelalov.roixcleanmvi.databinding.ItemMainBinding
import com.roix.semenbelalov.roixcleanmvi.R
import com.roix.semenbelalov.roixcleanmvi.databinding.FragmentMainBinding
import com.roix.semenbelalov.roixcleanmvi.ui.common.fragments.BaseToolbarListFragment
import com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels.MainViewModel

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

class MainFragment : BaseToolbarListFragment<MainViewModel, FragmentMainBinding, MainItem, ItemMainBinding>() {

    override val layoutId: Int = R.layout.fragment_main

    override val itemLayoutId: Int = R.layout.item_main

    override fun getRecyclerView(): RecyclerView = binding.rv

    override fun getSwipeToRefreshLayout(): SwipeRefreshLayout? = binding.srl


    override fun getToolbar(): Toolbar? = binding.toolbar.tb

    override fun setupBinding() {
        super.setupBinding()
        toolbarType.title.value = getString(R.string.title_main)
    }


}

