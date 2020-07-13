package com.bolddevoteam.adidaschallenge.core.repository

import com.bolddevoteam.adidaschallenge.core.commons.DB_ENTRY_ERROR
import com.bolddevoteam.adidaschallenge.core.domain.FailureDomain
import com.bolddevoteam.adidaschallenge.core.domain.HttpError
import com.bolddevoteam.adidaschallenge.core.domain.SuccessDomain
import retrofit2.Response

interface DomainPageMapper<T : Any> {
    fun mapToListModel(): T
}

interface RoomPageMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onPageSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onPageFailure(action: (HttpError) -> Unit) {
    if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}

inline fun <T : RoomPageMapper<R>, R : DomainPageMapper<U>, U : Any> Response<T>.getPageData(
        cacheAction: (R) -> Unit,
        fetchFromCacheAction: () -> R): U {
    try {
        onPageSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return databaseEntity.mapToListModel()
        }
        onPageFailure {
            val cachedModel = fetchFromCacheAction()
            if (cachedModel != null) SuccessDomain(cachedModel.mapToListModel()) else FailureDomain(HttpError(
                    Throwable(DB_ENTRY_ERROR)
            ))
        }
        return fetchFromCacheAction().mapToListModel()
    } catch (e: Exception) {
        return fetchFromCacheAction().mapToListModel()
    }
}
