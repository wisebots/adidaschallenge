package com.bolddevoteam.adidaschallenge.core.extensions

import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bolddevoteam.adidaschallenge.core.BuildConfig
import com.bolddevoteam.adidaschallenge.core.commons.TAG
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

fun onlyDebug() : Boolean {
    if (BuildConfig.DEBUG) return true
    return false
}

fun logDebug(className: String, message: String){
    Log.d(TAG, "$className $message")
}

fun logError(className: String, message: String){
    Log.e(TAG, "$className $message")
}

fun snackbar(@StringRes message: Int, rootView: View) = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

fun snackbar(message: String, rootView: View) = Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show()

inline fun ViewModel.launch(
        coroutineContext: CoroutineContext = CoroutineContextProvider().main,
        crossinline block: suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch(coroutineContext) { block() }
}

open class CoroutineContextProvider {
    open val main: CoroutineContext by lazy { Dispatchers.Main }
    open val io: CoroutineContext by lazy { Dispatchers.IO }
    open val default: CoroutineContext by lazy { Dispatchers.Default }
}

fun FragmentActivity.goBack() {
    supportFragmentManager.popBackStack()
}

fun FragmentActivity.showFragment(fragment: androidx.fragment.app.Fragment, @IdRes container: Int, addToBackStack: Boolean = false) {
    supportFragmentManager.beginTransaction().apply {
        if (addToBackStack) {
            addToBackStack(fragment.tag)
        }
    }
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(container, fragment)
            .commitAllowingStateLoss()
}

fun FragmentActivity.showFragment(fragment: Fragment, @IdRes container: Int, sharedElement: ImageView, addToBackStack: Boolean = false) {
    supportFragmentManager.beginTransaction().apply {
        if (addToBackStack) {
            addToBackStack(fragment.tag)
        }
    }
    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
    .addSharedElement(sharedElement, sharedElement.transitionName)
    .replace(container, fragment)
    .commitAllowingStateLoss()
}

inline fun <T> LiveData<T>.subscribe(owner: LifecycleOwner, crossinline onDataReceived: (T) -> Unit) =
    observe(owner, Observer { onDataReceived(it) })

fun <T> List<T>.random() = this.shuffled().take(1)[0]


