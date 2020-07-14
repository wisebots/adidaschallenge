package com.bolddevoteam.adidaschallenge.fitgoals.model.repository

import android.content.Context
import com.bolddevoteam.adidaschallenge.core.data.PhysicalMoves
import com.google.android.gms.fitness.HistoryClient
import com.google.android.gms.fitness.request.DataReadRequest
import io.reactivex.Single

class Repository {

    private val googleFitRepository = FitnessRepository()

    fun getPhysicalActivities(dataReadRequest: DataReadRequest, historyClient: HistoryClient, context: Context): Single<List<PhysicalMoves>> {
        return googleFitRepository.getPhysicalActivities(dataReadRequest, historyClient, context)
    }

}