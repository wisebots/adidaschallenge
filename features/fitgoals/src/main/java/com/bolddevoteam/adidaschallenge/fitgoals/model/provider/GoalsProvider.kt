package com.bolddevoteam.adidaschallenge.fitgoals.model.provider

import com.bolddevoteam.adidaschallenge.core.commons.DEFAULT_PROVIDER_ID
import com.bolddevoteam.adidaschallenge.core.domain.ResultDomain
import com.bolddevoteam.adidaschallenge.core.repository.BaseRepository
import com.bolddevoteam.adidaschallenge.core.repository.getData
import com.bolddevoteam.adidaschallenge.fitgoals.model.local.GoalsDao
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.Goals
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.GoalsApi
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.GoalsEntity

interface GoalsProvider {
    suspend fun getGoals(): ResultDomain<Goals>
}

class GoalsProviderImpl(private val api: GoalsApi,
                        private val dao: GoalsDao
) : BaseRepository<Goals, GoalsEntity>(),
    GoalsProvider {

    override suspend fun getGoals(): ResultDomain<Goals> {
        return fetchData(
            apiDataProvider = {
                api.getGoals().getData (
                    fetchFromCacheAction = { dao.getData(DEFAULT_PROVIDER_ID) },
                    cacheAction = { dao.saveData(it) }
                )
            },
            dbDataProvider = { dao.getData(DEFAULT_PROVIDER_ID) }
        )
    }
}