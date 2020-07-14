package com.bolddevoteam.adidaschallenge.fitgoals.model.repository

import android.content.Context
import com.bolddevoteam.adidaschallenge.core.data.PhysicalMoves
import com.bolddevoteam.adidaschallenge.core.extensions.toPhysicalActivityItem
import com.google.android.gms.fitness.HistoryClient
import com.google.android.gms.fitness.request.DataReadRequest
import com.google.android.gms.tasks.Tasks
import io.reactivex.Observable
import io.reactivex.Single

class FitnessRepository {

    fun getPhysicalActivities(dataReadRequest: DataReadRequest, historyClient: HistoryClient, context: Context): Single<List<PhysicalMoves>> {
        val task = historyClient.readData(dataReadRequest)

        return Observable.fromCallable { Tasks.await(task) }
            .switchMap { Observable.fromIterable(it.dataSets) }
            .filter { dataset -> !dataset.isEmpty }
            .flatMap { dataset -> Observable.fromIterable(dataset.dataPoints) }
            .map { datapoint -> datapoint.toPhysicalActivityItem(context) }
            .toList()
    }

}