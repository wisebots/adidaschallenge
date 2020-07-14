package com.bolddevoteam.adidaschallenge.fitgoals.model.repository

import com.bolddevoteam.adidaschallenge.core.domain.BaseUseCase
import com.bolddevoteam.adidaschallenge.core.domain.ResultDomain
import com.bolddevoteam.adidaschallenge.fitgoals.model.provider.GoalsProvider
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.Goals

interface GoalsRepository : BaseUseCase<Int, Goals> {
    override suspend operator fun invoke(): ResultDomain<Goals>
}

class GoalsRepositoryImpl(private val provider: GoalsProvider) : GoalsRepository {
    override suspend operator fun invoke() = provider.getGoals()
}