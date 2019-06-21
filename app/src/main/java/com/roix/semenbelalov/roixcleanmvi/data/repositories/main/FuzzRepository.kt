package com.roix.semenbelalov.roixcleanmvi.data.repositories.main



/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
class FuzzRepository : IFuzzRepository {
    override suspend fun getFuzz(): String {
        Thread.sleep(3000)
        return "Fuzz"
    }
}
