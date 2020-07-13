package com.bolddevoteam.adidaschallenge.fitgoals.view.fragments

import com.bolddevoteam.adidaschallenge.core.base.BaseFragment
import com.bolddevoteam.adidaschallenge.core.extensions.onClick
import com.bolddevoteam.adidaschallenge.core.extensions.snackbar
import com.bolddevoteam.adidaschallenge.fitgoals.R
import kotlinx.android.synthetic.main.fragment_goals.*

class GoalsFragment : BaseFragment() {

    override fun getLayout()= R.layout.fragment_goals

    override fun viewReady() {
        handleButtons()
    }

    private fun handleButtons() {
        bt_load.onClick { navigateTo(this, R.id.nav_goals_desc) }
    }

    private fun handleViewError(error: Throwable) {
        snackbar(getString(R.string.generic_error), clFragmentGoals)
    }

}