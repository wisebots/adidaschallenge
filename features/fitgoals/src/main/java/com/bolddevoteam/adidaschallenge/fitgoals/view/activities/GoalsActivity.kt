package com.bolddevoteam.adidaschallenge.fitgoals.view.activities

import android.os.Bundle
import com.bolddevoteam.adidaschallenge.core.base.BaseActivity
import com.bolddevoteam.adidaschallenge.fitgoals.R
import com.bolddevoteam.adidaschallenge.fitgoals.di.GoalsModule

class GoalsActivity : BaseActivity() {

    override fun getLayout() = R.layout.activity_goals

    private fun injectModules() = loadModules

    private val loadModules by lazy {
        GoalsModule.init()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectModules()
    }

}