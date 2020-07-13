package com.bolddevoteam.adidaschallenge.app

import android.os.Bundle
import com.bolddevoteam.adidaschallenge.R
import com.bolddevoteam.adidaschallenge.core.base.BaseActivity
import com.bolddevoteam.adidaschallenge.navigation.FeatureNavigation.home

class MainActivity : BaseActivity() {

    override fun getLayout() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigate()
    }

    fun navigate() {
        startActivity(home())
        finish()
    }
}