package com.bolddevoteam.adidaschallenge.fitgoals.view.fragments

import android.content.Intent
import androidx.recyclerview.widget.DividerItemDecoration
import com.bolddevoteam.adidaschallenge.core.base.BaseFragment
import com.bolddevoteam.adidaschallenge.core.commons.GOALS_PERMISSION
import com.bolddevoteam.adidaschallenge.core.extensions.snackbar
import com.bolddevoteam.adidaschallenge.core.extensions.subscribe
import com.bolddevoteam.adidaschallenge.core.view.Success
import com.bolddevoteam.adidaschallenge.core.view.Error
import com.bolddevoteam.adidaschallenge.core.view.ViewState
import com.bolddevoteam.adidaschallenge.fitgoals.R
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.GoalItem
import com.bolddevoteam.adidaschallenge.fitgoals.model.remote.Goals
import com.bolddevoteam.adidaschallenge.fitgoals.view.activities.SignInActivity
import com.bolddevoteam.adidaschallenge.view.adapter.GoalsAdapter
import com.bolddevoteam.adidaschallenge.viewmodel.GoalsViewModel
import kotlinx.android.synthetic.main.fragment_goals.*
import org.jetbrains.anko.support.v4.defaultSharedPreferences
import org.koin.androidx.viewmodel.ext.android.viewModel

class GoalsFragment : BaseFragment() {

    private var signedIn : Boolean = false

    private val viewModel: GoalsViewModel by viewModel()

    override fun getLayout()= R.layout.fragment_goals

    override fun viewReady() {
        subscribeGoals()
        handleSwipeRefresh()
    }

    override fun onResume() {
        super.onResume()
        signedIn = defaultSharedPreferences.getBoolean(GOALS_PERMISSION, false)
    }

    private fun subscribeGoals() {
        showLoading(pbwLoadingProgress)
        fragment_goals_rv.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        fragment_goals_rv.adapter = adapter
        viewModel.listGoals()
        viewModel.viewState.subscribe(this, ::handleViewState)
    }

    private fun handleViewState(viewState: ViewState<Goals>) {
        when (viewState) {
            is Success -> showInfoData(viewState.data)
            is Error -> handleRemoteError(viewState.error)
        }
    }

    private fun showInfoData(goals: Goals?) {
        goals?.let {
            adapter.submitList(it.items)
            adapter.notifyDataSetChanged()
        }
        hideLoading(pbwLoadingProgress)
        hideSwipeRefresh(swipeContainer)
    }

    private val itemClick: (GoalItem) -> Unit = {
        log("signedIn $signedIn")
        if(!signedIn) {
            startActivity(Intent(context, SignInActivity::class.java))
        }
       else navigateTo(this, R.id.nav_goals_desc)
    }

    private val adapter = GoalsAdapter(itemClick, signedIn)

    private fun handleViewError(error: Throwable) {
        snackbar(getString(R.string.generic_error), fragmentGoals)
        hideSwipeRefresh(swipeContainer)
    }

    private fun handleRemoteError(error: Throwable) {
        snackbar(getString(R.string.remote_error), fragmentGoals)
        hideSwipeRefresh(swipeContainer)
    }

    private fun handleSwipeRefresh() {
        showLoading(pbwLoadingProgress)
        hideSwipeRefresh(swipeContainer)
    }
}