package com.bolddevoteam.adidaschallenge.core.repository

import com.bolddevoteam.adidaschallenge.core.extensions.CoroutineContextProvider
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BasePageRepository<T : Any, R : DomainPageMapper<T>> : KoinComponent {

    private val connectivity: Connectivity by inject()
    private val contextProvider: CoroutineContextProvider by inject()

    protected suspend fun fetchDataList(
            apiDataProvider: suspend () -> T,
            dbDataProvider: suspend () -> T
    ): T {
        return if(isLocalCachedRules()) {
            withContext(contextProvider.io) {
                dbDataProvider()
            }
        }
        else {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        }
    }

    private fun isLocalCachedRules() : Boolean {
        if(connectivity.hasNetworkAccess()) return false
        return true
    }

}