package com.bolddevoteam.adidaschallenge.core.domain

sealed class ResultDomain<out T : Any>

data class SuccessDomain<out T : Any>(val data: T) : ResultDomain<T>()
data class FailureDomain(val httpError: HttpError) : ResultDomain<Nothing>()
data class FailureLocalDomain(val ioError: IOError) : ResultDomain<Nothing>()

class HttpError(val throwable: Throwable, val errorCode: Int = 0)

class IOError(val throwable: Throwable, val errorCode: Int = 0)

inline fun <T : Any> ResultDomain<T>.onSuccess(action: (T) -> Unit): ResultDomain<T> {
    if (this is SuccessDomain) action(data)
    return this
}

inline fun <T : Any> ResultDomain<T>.onFailure(action: (HttpError) -> Unit) {
    if (this is FailureDomain) action(httpError)
}

inline fun <T : Any> ResultDomain<T>.onLocalFailure(action: (IOError) -> Unit) {
    if (this is FailureLocalDomain) action(ioError)
}
