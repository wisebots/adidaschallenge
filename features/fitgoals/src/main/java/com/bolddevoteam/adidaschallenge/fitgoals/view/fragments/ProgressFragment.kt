package com.bolddevoteam.adidaschallenge.fitgoals.view.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bolddevoteam.adidaschallenge.core.base.BaseFragment
import com.bolddevoteam.adidaschallenge.core.extensions.visible
import com.bolddevoteam.adidaschallenge.fitgoals.R
import com.bolddevoteam.adidaschallenge.fitgoals.view.activities.SignInActivity
import com.bolddevoteam.adidaschallenge.viewmodel.ProgressViewModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.fitness.Fitness
import kotlinx.android.synthetic.main.fragment_progress.*

class ProgressFragment : BaseFragment() {

    private val GOOGLE_FIT_PERMISSIONS_REQUEST_CODE = 999

    private var googleFitPermissionGranted: Boolean = false

    private lateinit var viewModel: ProgressViewModel

    override fun getLayout()= R.layout.fragment_progress

    override fun viewReady() {
        viewModel = ViewModelProviders.of(this).get(ProgressViewModel::class.java)
        checkGoogleFitPermissions(requireContext())
        subscribeData()
        publishData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            this.googleFitPermissionGranted = requestCode == GOOGLE_FIT_PERMISSIONS_REQUEST_CODE
            publishData()
        } else startActivity(
            Intent(context, SignInActivity::class.java
        )
        )
    }

    private fun checkGoogleFitPermissions(context: Context) {
        googleFitPermissionGranted = GoogleSignIn.hasPermissions(GoogleSignIn.getLastSignedInAccount(activity), fitnessOptions)
    }

    private fun subscribeData() {
        with (viewModel) {
            physicalActivityTime.observe(this@ProgressFragment, Observer {
                stepsValue.visible()
                stepsValue.text = "${it / (1000 * 60)} minutes"
            })
        }
    }

    private fun publishData() {
        if (googleFitPermissionGranted) {
            val lastSignedInAccount = GoogleSignIn.getLastSignedInAccount(requireContext())
            lastSignedInAccount?.let { account ->
                viewModel.loadData(requireContext(), Fitness.getHistoryClient(requireContext(), account))
            }
        }
        else startActivity(Intent(context, SignInActivity::class.java))
    }
}