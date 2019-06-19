package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

import android.util.Log
import com.roix.semenbelalov.roixcleanmvi.buissness.main.IMainInteractor
import com.roix.semenbelalov.roixcleanmvi.data.models.MainItem
import com.roix.semenbelalov.roixcleanmvi.di.main.MainModule
import com.roix.semenbelalov.roixcleanmvi.mvi.channel.IRoixChannel
import com.roix.semenbelalov.roixcleanmvi.mvi.channel.RoixChannel
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
class MainViewModel : BaseListViewModel<MainItem>(),
    IRoixChannel<MainEvents> by RoixChannel(
        CoroutineScope(SupervisorJob() + Dispatchers.IO),
        CoroutineScope(SupervisorJob() + Dispatchers.Main)
    ) {


    @Inject
    protected lateinit var interactor: IMainInteractor

    override val module: Module = MainModule()


    val events = EventFlow<MainEvents>(CoroutineScope(SupervisorJob() + Dispatchers.IO))

    init {

        go(MainUseCase())
            .to(Started("Hello"), MainReducer())
            .sub {
                items.update(emptyList())
                Log.d("roix mvi", "sub $it + ${Thread.currentThread().name}")
            }

//        events.from { event ->
//            Log.d("roix mvi", "from $event")
//            Thread.sleep(3000)
//            return@from BuzzUpdate("world")
//        }.to(Started("Hi")) { state, update ->
//            Log.d("roix mvi", "to $state $update")
//            return@to state.copy(text = "dadas")
//        }.bind { state ->
//            Log.d("roix mvi", "bind $state")
//        }
//
//        events.from(MainUseCase())
//            .to(Started("hello"), MainReducer())
//            .bind { state ->
//                Log.d("roix mvi", "bind $state")
//            }
//
//        events.from { event ->
//            Log.d("roix mvi", "from $event")
//            Thread.sleep(3000)
//            return@from BuzzUpdate("world")
//        }.to(Started("Hi")) { state, update ->
//            Log.d("roix mvi", "to $state $update")
//            return@to state.copy(text = "dadas")
//        }.bind { state ->
//            Log.d("roix mvi", "bind $state")
//        }
    }

    fun onEvent() {
        pub(OnButtonClicked())
    }


}
