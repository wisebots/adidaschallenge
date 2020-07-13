package com.bolddevoteam.adidaschallenge.core.repository

import com.bolddevoteam.adidaschallenge.core.commons.DB_ENTRY_ERROR
import com.bolddevoteam.adidaschallenge.core.commons.GENERAL_DATABASE_ERROR
import com.bolddevoteam.adidaschallenge.core.domain.*
import com.bolddevoteam.adidaschallenge.core.extensions.CoroutineContextProvider
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository<T : Any, R : DomainMapper<T>> : KoinComponent {

    private val connectivity: Connectivity by inject()
    private val contextProvider: CoroutineContextProvider by inject()

    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchData (
        apiDataProvider: suspend () -> ResultDomain<T>,
        dbDataProvider: suspend () -> R?
    ): ResultDomain<T> {
        return if(whenRemoteCachedRules()) {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult == null) apiDataProvider()
                else SuccessDomain(dbResult.mapToDomainModel())
            }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) SuccessDomain(dbResult.mapToDomainModel())
                else FailureDomain(HttpError(Throwable(DB_ENTRY_ERROR)))
            }
        }
    }

    /**
     * Use this when communicating only with local database
     */
    protected suspend fun fetchData (
        dbDataProvider: suspend () -> R?
    ): ResultDomain<T> {
        return if(whenLocalCachedRules()) {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) SuccessDomain(dbResult.mapToDomainModel())
                else FailureLocalDomain(IOError(Throwable(DB_ENTRY_ERROR)))
            }
        } else {
            FailureLocalDomain(IOError(Throwable(GENERAL_DATABASE_ERROR)))
        }
    }

    /**
     * Use this when communicating only with local database with actions
     */
    protected suspend fun fetchActionData (
        dbDataProvider: suspend () -> R?
    ): ResultDomain<T> {
        return if(whenLocalCachedRules()) {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) SuccessDomain(dbResult.mapToDomainModel())
                else FailureLocalDomain(IOError(Throwable(DB_ENTRY_ERROR)))
            }
        } else {
            FailureLocalDomain(IOError(Throwable(GENERAL_DATABASE_ERROR)))
        }
    }

    private fun whenRemoteCachedRules() : Boolean {
        if(connectivity.hasNetworkAccess()) return true
        return false
    }

    private fun whenLocalCachedRules() = true

}