package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

import android.util.Log
import com.roix.semenbelalov.roixcleanmvi.buissness.main.IMainInteractor
import com.roix.semenbelalov.roixcleanmvi.data.models.MainItem
import com.roix.semenbelalov.roixcleanmvi.di.main.MainModule
import com.roix.semenbelalov.roixcleanmvi.mvi.ui.EventFlow
import com.roix.semenbelalov.roixcleanmvi.ui.common.viewmodels.BaseListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import toothpick.config.Module
import javax.inject.Inject

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
class MainViewModel : BaseListViewModel<MainItem>() {


    @Inject
    protected lateinit var interactor: IMainInteractor


    val events = EventFlow<MainEvents>(CoroutineScope(SupervisorJob() + Dispatchers.IO))

    override val module: Module = MainModule()

    init {

        events.from(MainUseCase())
            .to(Started("hello"), MainReducer())
            .bind { state ->
                Log.d("roix mvi", "bind $state")
            }

        events.from { event ->
            Log.d("roix mvi", "from $event")
            Thread.sleep(3000)
            BuzzUpdate("world")
        }.to(Started("Hi")) { started, buzzUpdate ->
            Log.d("roix mvi", "to $started $buzzUpdate")
            started
        }.bind { state ->
            Log.d("roix mvi", "bind $state")
        }
    }

    fun onEvent() {
//        machine.publish(OnButtonClicked())
        events.publish(OnButtonClicked())


    }


}
