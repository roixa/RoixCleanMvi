package com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels

import android.util.Log
import com.roix.semenbelalov.roixcleanmvi.buissness.main.IMainInteractor
import com.roix.semenbelalov.roixcleanmvi.data.models.MainItem
import com.roix.semenbelalov.roixcleanmvi.di.main.MainModule
import com.roix.semenbelalov.roixcleanmvi.mvi.ui.CoroutinesStateMachine
import com.roix.semenbelalov.roixcleanmvi.mvi.ui.EventFlow
import com.roix.semenbelalov.roixcleanmvi.mvi.ui.StateMachine
import com.roix.semenbelalov.roixcleanmvi.ui.common.viewmodels.BaseListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import toothpick.config.Module
import javax.inject.Inject

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
class MainViewModel : BaseListViewModel<MainItem>(), StateMachine<MainEvents, MainUpdates, MainStates> {


    @Inject
    protected lateinit var interactor: IMainInteractor

    private val machine = CoroutinesStateMachine(Started("hello "), this)

    val events = EventFlow<MainEvents>(CoroutineScope(SupervisorJob() + Dispatchers.IO))

    override val module: Module = MainModule()

    init {
        events.from(MainInteractors())
            .to(Started("hello"), MainReducer())
            .bind { state ->
                Log.d("roix mvi", "bind $state")
            }
    }

    fun onEvent() {
//        machine.publish(OnButtonClicked())
        events.publish(OnButtonClicked())


    }

    override suspend fun handleUpdates(event: MainEvents): Flow<MainUpdates>? = null

    override suspend fun handleUpdate(event: MainEvents): MainUpdates? {
        Log.d("roix mvi", "handleUpdate $event")

        return FuzzUpdate(interactor.getText())
    }

    override val updateState: (lastState: MainStates, update: MainUpdates) -> MainStates = { lastState, update ->
        Log.d("roix mvi", "updateState $update")

        when (update) {
            is FuzzUpdate -> Process(update.text)
            is BuzzUpdate -> lastState
        }
    }

    override fun bind(state: MainStates) {
        Log.d("roix mvi", "result is $state")
    }

    override fun onCleared() {
        super.onCleared()
        close()
    }

}
