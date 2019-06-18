package com.roix.semenbelalov.roixcleanmvi.buissness.main

import com.roix.semenbelalov.roixcleanmvi.data.repositories.main.IMainRepository
import javax.inject.Inject

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */

class MainInteractor @Inject constructor(val repository: IMainRepository) : IMainInteractor {

    override suspend fun getText(): String {
        Thread.sleep(1000)
        return "world"
    }
}