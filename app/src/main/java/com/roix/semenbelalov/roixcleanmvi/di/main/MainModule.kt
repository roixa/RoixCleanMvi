package com.roix.semenbelalov.roixcleanmvi.di.main

import com.roix.semenbelalov.roixcleanmvi.buissness.main.MultiAddUseCase
import com.roix.semenbelalov.roixcleanmvi.buissness.main.SingleAddUseCase
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.BuzzRepository
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.FuzzRepository
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IBuzzRepository
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IFuzzRepository
import com.roix.semenbelalov.roixcleanmvi.ui.main.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
val mainModule = module {
    single<IBuzzRepository> { BuzzRepository() }
    single<IFuzzRepository> { FuzzRepository() }
    single { SingleAddUseCase(get(), get()) }
    single { MultiAddUseCase(get()) }
    viewModel { MainViewModel(get(), get()) }
}