package com.bolddevoteam.adidaschallenge.core.base

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.bolddevoteam.adidaschallenge.core.commons.EMPTY_STRING
import com.bolddevoteam.adidaschallenge.core.extensions.gone
import com.bolddevoteam.adidaschallenge.core.extensions.logDebug
import com.bolddevoteam.adidaschallenge.core.extensions.snackbar
import com.bolddevoteam.adidaschallenge.core.extensions.visible
import com.bolddevoteam.adidaschallenge.core.widgets.ProgressBarWidget

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        log("onCreate activity")

        window.decorView.apply {
            systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        }
    }

    abstract fun getLayout(): Int

    private var currentFragment : Fragment? = null

    fun showError(@StringRes errorMessage: Int, rootView: View) = snackbar(errorMessage, rootView)

    fun showError(errorMessage: String?, rootView: View) = snackbar(errorMessage ?: EMPTY_STRING, rootView)

    fun showLoading(progressBar: ProgressBarWidget?) = progressBar?.visible()

    fun hideLoading(progressBar: ProgressBarWidget?) = progressBar?.gone()

    fun log(message: String) = logDebug("${this::class.java.canonicalName}", message)

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount < 1
            && currentFragment == null) finish()
        else {
            NavHostFragment.findNavController(currentFragment!!).popBackStack()
            currentFragment = null
        }
    }

    fun addCurrentFragment(fragment: Fragment) {
        currentFragment = fragment
    }
}