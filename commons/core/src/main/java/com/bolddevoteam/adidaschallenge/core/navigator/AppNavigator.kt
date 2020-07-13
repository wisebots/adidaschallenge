package com.bolddevoteam.adidaschallenge.core.navigation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph

class AppNavigator(private val activity: AppCompatActivity) : Navigator {

    override fun navigateTo(navController: NavController, navGraph: NavGraph){
        navController.navigate(navGraph.id)
    }

    override fun navigateTo(intent: Intent) = activity.startActivity(intent)

}