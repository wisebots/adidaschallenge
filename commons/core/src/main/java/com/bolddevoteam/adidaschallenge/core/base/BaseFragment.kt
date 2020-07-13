package com.bolddevoteam.adidaschallenge.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.transition.TransitionInflater
import com.bolddevoteam.adidaschallenge.core.extensions.gone
import com.bolddevoteam.adidaschallenge.core.extensions.logDebug
import com.bolddevoteam.adidaschallenge.core.extensions.visible
import com.bolddevoteam.adidaschallenge.core.widgets.ProgressBarWidget

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        sharedElementEnterTransition = TransitionInflater
                .from(context).inflateTransition(
                        android.R.transition.move
                )
        return inflater.inflate(getLayout(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        log("onCreate fragment")
        addCurrentFragment()
        viewReady()
    }

    abstract fun viewReady()

    abstract fun getLayout(): Int

    fun log(message: String){
        logDebug("${this::class.java.canonicalName}", message)
    }

    fun showLoading(progressBar: ProgressBarWidget?) = progressBar?.visible()

    fun hideLoading(progressBar: ProgressBarWidget?) = progressBar?.gone()

    fun addCurrentFragment() = (activity as BaseActivity).addCurrentFragment(this)

    fun navigateTo(navDirections: NavDirections) = NavHostFragment.findNavController(this).navigate(navDirections)

    fun navigateTo(fragment: Fragment, resourceId: Int)= findNavController(fragment).navigate(resourceId)

}