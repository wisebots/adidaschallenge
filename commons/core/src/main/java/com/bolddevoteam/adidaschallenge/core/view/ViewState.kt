package com.bolddevoteam.adidaschallenge.core.view

sealed class ViewState<out T : Any>
class Success<out T : Any>(val data: T) : ViewState<T>()
class Error<out T : Any>(val error: Throwable) : ViewState<T>()
class Loading<out T : Any> : ViewState<T>()
class NothingShow<out T : Any> : ViewState<T>()
class NoInternetState<T : Any> : ViewState<T>()

sealed class UiState {
    object Complete : UiState()
    object Loading : UiState()
    object Empty: UiState()
    class Error(val throwable: Throwable) : UiState()
}