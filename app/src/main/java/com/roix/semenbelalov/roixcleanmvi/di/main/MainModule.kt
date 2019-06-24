package com.roix.semenbelalov.roixcleanmvi.di.main

import com.roix.semenbelalov.roixcleanmvi.buissness.main.MultiAddUseCase
import com.roix.semenbelalov.roixcleanmvi.buissness.main.SingleAddUseCase
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.*
import com.roix.semenbelalov.roixcleanmvi.ui.main.models.MainReducer
import com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
val mainModule = module {
    single<IStepRepository> { StepRepository() }
    single<IBuzzRepository> { BuzzRepository() }
    single<IFuzzRepository> { FuzzRepository() }
    single { SingleAddUseCase(get(), get(), get()) }
    single { MultiAddUseCase(get(), get(), get()) }
    single { MainReducer() }
    viewModel { MainViewModel(get(), get(), get()) }
}