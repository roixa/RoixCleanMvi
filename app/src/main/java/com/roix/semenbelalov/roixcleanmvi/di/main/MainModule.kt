package com.roix.semenbelalov.roixcleanmvi.di.main

import com.roix.semenbelalov.roixcleanmvi.buissness.main.IMainInteractor
import com.roix.semenbelalov.roixcleanmvi.buissness.main.MainInteractor
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.MainRepository
import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IMainRepository
import toothpick.config.Module

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
class MainModule : Module() {

    init {
        bind(IMainInteractor::class.java).to(MainInteractor::class.java).instancesInScope()
        bind(IMainRepository::class.java).to(MainRepository::class.java).instancesInScope()
    }

}