package com.bolddevoteam.adidaschallenge.core.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.transition.TransitionInflater
import com.bolddevoteam.adidaschallenge.core.extensions.gone
import com.bolddevoteam.adidaschallenge.core.extensions.logDebug
import com.bolddevoteam.adidaschallenge.core.extensions.visible
import com.bolddevoteam.adidaschallenge.core.widgets.ProgressBarWidget
import com.google.android.gms.fitness.FitnessOptions
import com.google.android.gms.fitness.data.DataType

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

    fun hideLoading(progressBar: ProgressBarWidget?) {
        val animate = AnimationUtils.loadAnimation(context, android.R.anim.fade_out)
        progressBar?.startAnimation(animate)
        progressBar?.gone()
    }

    fun hideSwipeRefresh(swipeContainer: SwipeRefreshLayout) {
        swipeContainer.isRefreshing = false
    }

    fun addCurrentFragment() = (activity as BaseActivity).addCurrentFragment(this)

    fun navigateTo(navDirections: NavDirections) = NavHostFragment.findNavController(this).navigate(navDirections)

    fun navigateTo(fragment: Fragment, resourceId: Int)= findNavController(fragment).navigate(resourceId)

    val fitnessOptions: FitnessOptions = FitnessOptions
        .builder()
        .addDataType(DataType.TYPE_ACTIVITY_SEGMENT, FitnessOptions.ACCESS_READ)
        .build()

}