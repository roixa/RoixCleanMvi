package com.roix.semenbelalov.roixcleanmvi.data.repositories.main

/**
 * Created by roix template
 * https://github.com/roixa/RoixArchitectureTemplates
 */
interface IFuzzRepository {
    suspend fun getFuzz(): String
}