package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

import android.util.Log
import com.roix.semenbelalov.roixcleanmvi.data.models.MainItem
import com.roix.semenbelalov.roixcleanmvi.di.main.MainModule
import com.roix.semenbelalov.roixcleanmvi.mvi.channel.IRoixChannel
import com.roix.semenbelalov.roixcleanmvi.mvi.channel.RoixChannel
import com.roix.semenbelalov.roixcleanmvi.ui.common.viewmodels.BaseListViewModel
import toothpick.config.Module

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
class MainViewModel : BaseListViewModel<MainItem>(),
    IRoixChannel<MainEvents> by RoixChannel() {

    override val module: Module = MainModule()

    init {

        go(MainUseCase())
            .reduce(Started("Hello"), MainReducer())
            .sub {
                items.update(emptyList())
                Log.d("roix mvi", "sub $it + ${Thread.currentThread().name}")
            }

        cast<OnButtonClicked>()
        cast<MainEvents>()
            .go(MainUseCase())
            .with(
                go(MainUseCase())
            )
            .reduce(Started("HS"), MainReducer()).sub {
                Log.d("roix mvi", "sub $it + ${Thread.currentThread().name}")

            }

        go { event ->
            Log.d("roix mvi", "from $event")
            Thread.sleep(3000)
            return@go BuzzUpdate("world")
        }.reduce(Started("Hi")) { state, update ->
            Log.d("roix mvi", "reduce $state $update")
            return@reduce state.copy(text = "dadas")
        }.sub { state ->
            Log.d("roix mvi", "bind $state")
        }
    }

    fun onEvent() {
        pub(OnButtonClicked())
    }


}
