package com.roix.semenbelalov.roixcleanmvi.data.repositories.main

class BuzzRepository : IBuzzRepository {
    override suspend fun getBuzz(): String {
        Thread.sleep(4000)
        return "Buzz"
    }
}