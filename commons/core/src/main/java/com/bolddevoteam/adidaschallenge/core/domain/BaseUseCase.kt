package com.bolddevoteam.adidaschallenge.core.domain

interface BaseUseCase<T : Any, R: Any> {
    suspend operator fun invoke(): ResultDomain<R>
}

interface BaseActionUseCase<T : Any, K : Any, R: Any> {
    suspend operator fun invoke(action: ActionType, item: Any): ResultDomain<R>
}

interface BaseParamUseCase<T : Any, K : Any, R: Any> {
    suspend operator fun invoke(param: String): ResultDomain<R>
}

interface BaseParamActionUseCase<T : Any, K : Any, R: Any> {
    suspend operator fun invoke(param: String, action: ActionType, item: Any): ResultDomain<R>
}

interface BasePageUseCase<T : Any, K : Any, R: Any> {
    suspend operator fun invoke(page: T): R
}

interface BaseParamPageUseCase<T : Any, K : Any, R: Any> {
    suspend operator fun invoke(param: T, page: K): R
}

interface PagedUseCase<T : Any, K : Any, R: Any> {
    suspend operator fun invoke(page: T): List<R>?
}

interface WebhookUseCase<T : Any, K : Any> {
    suspend operator fun invoke(uid: String, key: Any, value: Any)
}

enum class ActionType{ UNCHANGE, REMOVE, INSERT, SHUFFLE, INVERT }
