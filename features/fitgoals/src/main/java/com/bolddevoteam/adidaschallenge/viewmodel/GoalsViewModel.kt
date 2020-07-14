package com.bolddevoteam.adidaschallenge.viewmodel

import com.bolddevoteam.adidaschallenge.core.domain.onFailure
import com.bolddevoteam.adidaschallenge.core.domain.onSuccess
import com.bolddevoteam.adidaschallenge.core.view.Success
import com.bolddevoteam.adidaschallenge.core.view.Error
import com.bolddevoteam.adidaschallenge.core.viewmodel.BaseViewModel
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.Goals
import com.bolddevoteam.adidaschallenge.fitgoals.model.repository.GoalsRepository

class GoalsViewModel(private val goalsRepository: GoalsRepository
) : BaseViewModel<Goals, GoalsViewData>() {

    fun listGoals() = executeUseCase {
        goalsRepository()
            .onSuccess { _viewState.value = Success(it) }
            .onFailure { _viewState.value = Error(it.throwable) }
    }
    fun onRefresh()= listGoals()

}

sealed class GoalsViewData