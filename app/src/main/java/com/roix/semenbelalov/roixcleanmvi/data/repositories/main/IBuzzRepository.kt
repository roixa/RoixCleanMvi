package com.roix.semenbelalov.roixcleanmvi.data.repositories.main

interface IBuzzRepository {
    suspend fun getBuzz(): String
}